package com.practice.library.service.impl;

import com.practice.library.ORMConfig;
import com.practice.library.dto.RentRequestDTO;
import com.practice.library.model.Book;
import com.practice.library.model.Member;
import com.practice.library.model.Rent;
import com.practice.library.repository.BookRepository;
import com.practice.library.repository.RentRepository;
import com.practice.library.service.interfaces.RentService;
import exception.MyException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RentServiceImpl implements RentService {
    @Autowired
    RentRepository rentRepository;
    @Autowired
    BookRepository bookRepository;

    public Rent rentBook(RentRequestDTO rentDTO) throws MyException {
        try (Session session = ORMConfig.getSession()) {
            Member member = session.get(Member.class, rentDTO.getMemberId());
            Book book = session.get(Book.class, rentDTO.getBookId());
            Rent rent = new Rent();

            rentMemberValidation(rentDTO.getMemberId());
            rentBookValidation(book,rentDTO.getBookId());

            rent.setBook(book);
            rent.setMember(member);
            rent.setRentDate(new Date());
            rent.setReturned(false);
            return rentRepository.rentBook(session, rent);
        }
    }

    public boolean returnBook(long rentId) throws MyException {
        try (Session session = ORMConfig.getSession()) {
            Rent rent = session.get(Rent.class, rentId);
            if (rent == null) {
                throw new MyException(MyException.OBJECT_NOT_FOUND," این شماره امانت موجود نمیباشد.");
            }
            rent.setReturned(true);
            rent.setReturnDate(new Date());
            session.beginTransaction();
            session.update(rent);
            session.getTransaction().commit();
        }
        return true;
    }


    public List<Rent> rentBooks() {
        List<Rent> rentList = new ArrayList<>();
        for (Rent rent : rentRepository.rentBooks()) {
            if (!rent.isReturned()) {
                rentList.add(rent);
            }
        }
        return rentList;
    }

     private void rentBookValidation (Book book,long bookId) throws MyException{
         if (book == null || bookRepository.bookExist(bookId) >= book.getCount()) {
             throw new MyException(MyException.BOOK_IS_NOT_AVAILABLE,"کتاب موجود نیست.");
         }
     }
    private void rentMemberValidation (long memberId) throws MyException{
        if (rentRepository.getRentBooks(memberId) >= 3) {
            throw new MyException(MyException.INVALID_COUNT_BOOK,"تعداد کتابهای امانت گرفته شده بیش از حد مجاز میباشد.");
        }
    }
}