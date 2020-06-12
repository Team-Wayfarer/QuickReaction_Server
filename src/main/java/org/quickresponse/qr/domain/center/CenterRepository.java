package org.quickresponse.qr.domain.center;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.user.User;
import org.quickresponse.qr.service.center.dto.CenterLoginRequestDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
@RequiredArgsConstructor
public class CenterRepository {

    private final EntityManager em;

    public Center login(CenterLoginRequestDto dto) {
        String query = "select c from Center c where c.id = ?1 and c.password =?2";
        TypedQuery<Center> jpql = em.createQuery(query, Center.class);
        jpql.setParameter(1, dto.getId());
        jpql.setParameter(2, dto.getPassword());
        Center center = jpql.getSingleResult();
        return center;
    }
}
