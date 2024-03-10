package com.practice.library.repository;

import com.practice.library.ORMConfig;
import com.practice.library.model.Member;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class MemberRepository {
    public Member addMember(Member member) {
        try (Session session = ORMConfig.getSession()) {
            session.beginTransaction();
            session.save(member);
            session.getTransaction().commit();
            return member;
        }
    }

    public List getMemberListByName(String memberName) {
        Query query;
        try (Session session = ORMConfig.getSession()) {
            String hql = (" from Member m where m.name Like  :memberName  ");
            query = session.createQuery(hql);
            query.setParameter("memberName", "%" + memberName + "%");
            return query.getResultList();
        }
    }
}
