package com.practice.library.repository;

import com.practice.library.ORMConfig;
import com.practice.library.model.Book;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class BookRepository {
    public Book addBook(Book book) {
        try (Session session = ORMConfig.getSession()) {
            session.beginTransaction();
            session.save(book);
            session.getTransaction().commit();
            return book;
        }
    }

    public List getBookListByName(String bookName) {
        Query query;
        try (Session session = ORMConfig.getSession()) {
            String hql = (" from Book b where b.name Like  :bookName  ");
            query = session.createQuery(hql);
            query.setParameter("bookName", "%" + bookName + "%");
            return query.getResultList();
        }
    }

    public Long bookExist(long bookId) {
        try (Session session = ORMConfig.getSession()) {
            Query query;
            String hql = ("select  count(1) from Rent r where r.book.id=:bookId and r.returned=:returned ");
            query = session.createQuery(hql, Long.class);
            query.setParameter("bookId", bookId);
            query.setParameter("returned", false);
            return (Long) query.getSingleResult();
        }
    }

}
