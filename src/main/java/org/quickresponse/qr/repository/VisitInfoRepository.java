package org.quickresponse.qr.repository;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.VisitInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class VisitInfoRepository {

    private final EntityManager em;

    public VisitInfo save(VisitInfo visitInfo){
        em.persist(visitInfo);
        return visitInfo;
    }
}
