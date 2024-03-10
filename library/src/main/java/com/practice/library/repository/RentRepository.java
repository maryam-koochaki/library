package com.practice.library.repository;

import com.practice.library.ORMConfig;
import com.practice.library.model.Rent;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Repository
public class RentRepository {
    public Rent rentBook(Session session, Rent rent) {
        session.beginTransaction();
        session.save(rent);
        session.getTransaction().commit();
        return rent;
    }

    public List<Rent> rentBooks() {
        try (Session session = ORMConfig.getSession()) {
            Query query;
            String hql = ("select r from Rent r where DateDiff(day,r.rentDate,:today) >14 ");
            query = session.createQuery(hql);
            query.setParameter("today", new Date());
            return (List<Rent>) query.getResultList();

        }
    }

    public Long getRentBooks(long memberId) {
        try (Session session = ORMConfig.getSession()) {
            Query query;
            String hql = ("SELECT count(1) FROM Rent r where r.member.id=:memberId and r.returned=:returned");
            query = session.createQuery(hql, Long.class);
            query.setParameter("memberId", memberId);
            query.setParameter("returned", false);
            return (Long) query.getSingleResult();
        }
    }
}
