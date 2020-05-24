package org.quickresponse.qr.repository;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.User;
import org.quickresponse.qr.dto.UserLoginDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public long login(UserLoginDto dto) {
        String query = "select u from User u where u.name = ?1";
        TypedQuery<User> jpql = em.createQuery(query, User.class);
        jpql.setParameter(1, dto.getName());
        User user = jpql.getSingleResult();
        return user.getId();
    }

    public User findById(Long id) {
        return em.find(User.class, id);
    }

    public void updateContact(long id, String contact) {

    }
}
