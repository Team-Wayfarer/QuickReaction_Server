package org.quickresponse.qr.domain.visitInfo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VisitInfoRepository {

    private final EntityManager em;

    public VisitInfo save(VisitInfo visitInfo) {
        em.persist(visitInfo);
        return visitInfo;
    }

    public List<VisitInfo> findVisitInfoListByUserId(Long id, int offset, int limit) {
        return em.createQuery("select v from VisitInfo v" +
                " where v.user.id =: id order by v.localDateTime desc", VisitInfo.class)
                .setParameter("id", id)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public List<VisitInfo> findVisitInfoListBySpotId(Long id) {
       return em.createQuery("select v from VisitInfo v"+
                            " where v.spot.id =: id", VisitInfo.class)
               .setParameter("id",id)
                .getResultList();
    }
}
