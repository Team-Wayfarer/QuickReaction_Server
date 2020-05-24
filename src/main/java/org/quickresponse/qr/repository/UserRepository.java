package org.quickresponse.qr.repository;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.User;
import org.quickresponse.qr.dto.UserLoginDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager entityManager;

    public void save(User user) {
        entityManager.persist(user);
    }

    public boolean login(UserLoginDto dto) {
        Query query = entityManager.createQuery("select count(id) from User where name = ?1 and contact = ?2");
        query.setParameter(1, dto.getName());
        query.setParameter(2, dto.getContact());
        return (int) query.getSingleResult() > 0;
    }

    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    public void updateContact(long id, String contact) {

    }
}
