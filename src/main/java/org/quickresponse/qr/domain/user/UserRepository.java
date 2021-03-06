package org.quickresponse.qr.domain.user;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.visitInfo.VisitInfo;
import org.quickresponse.qr.service.user.dto.UserLoginDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public User save(User user) {
        em.persist(user);
        return user;
    }

    public long login(UserLoginDto dto) {
        String query = "select u from User u where u.email = ?1";
        TypedQuery<User> jpql = em.createQuery(query, User.class);
        jpql.setParameter(1, dto.getEmail());
        User user = jpql.getSingleResult();
        return user.getId();
    }

    public User findById(Long id) {
//        String query = "select u from User u" +
//                " join fetch VisitInfo " +
//                " where "
        return em.find(User.class, id);
    }

    public Optional<User> findByEmail(String email) {
        String query = "select u from User u where u.email = ?1";
        TypedQuery<User> jpql = em.createQuery(query, User.class);
        jpql.setParameter(1, email);
        return jpql.setMaxResults(1)
                .getResultList()
                .stream()
                .findFirst();
    }

    public void updateContact(long id, String contact) {

    }
}
