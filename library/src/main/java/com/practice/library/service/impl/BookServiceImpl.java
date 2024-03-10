package com.practice.library.service.impl;

import com.practice.library.dto.BookRequestDTO;
import com.practice.library.model.Book;
import com.practice.library.repository.BookRepository;
import com.practice.library.service.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    public Book addBook(BookRequestDTO bookRequestDTO) {
        Book book = new Book();
        book.setCount(bookRequestDTO.getCount());
        book.setName(bookRequestDTO.getName());
        return bookRepository.addBook(book);
    }

    public List<Book> getBookListByName(String bookName) {

        return (List<Book>) bookRepository.getBookListByName(bookName);
    }
}