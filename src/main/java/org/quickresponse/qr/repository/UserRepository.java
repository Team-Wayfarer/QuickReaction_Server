package org.quickresponse.qr.repository;

import org.quickresponse.qr.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    public void join(User user) {
        em.persist(user);
    }

}
