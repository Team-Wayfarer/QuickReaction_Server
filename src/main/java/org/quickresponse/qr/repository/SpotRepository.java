package org.quickresponse.qr.repository;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.Spot;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SpotRepository {

    private final EntityManager em;

    public Long join(Spot spot){
        em.persist(spot);
        return spot.getId();
    }

    public Spot findOne(Long id){
        return em.find(Spot.class, id);
    }

    public List<Spot> findAll(){
        return em.createQuery("select s from Spot s ",Spot.class)
                .getResultList();
    }

    public List<Spot> findOneDetails(Long id, int offset, int limit){
        return em.createQuery("select s from Spot s"+
                " join fetch s.visitInfoList v" +
                " where s.id =: id  order by v.id desc", Spot.class)
                .setParameter("id", id)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}