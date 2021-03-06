package org.quickresponse.qr.domain.spot;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.spot.Spot;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SpotRepository {

    private final EntityManager em;

    public Spot join(Spot spot){
        em.persist(spot);
        return spot;
    }

    public Spot findOne(Long id){
        return em.find(Spot.class, id);
    }

    public List<Spot> findAll(){
        return em.createQuery("select s from Spot s ",Spot.class)
                .getResultList();
    }

    public List<Spot> findOneDetails(Long id, int offset, int limit){
        if(findOne(id)==null) {
            throw new NullPointerException("등록된 spot이 아닙니다.");
        }

        return em.createQuery("select s from Spot s"+
                " join fetch s.visitInfoList v" +
                " where s.id =: id  order by v.id desc", Spot.class)
                .setParameter("id", id)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public Spot findSpotBySpotAdminId( Long id ){
        try {
            return em.createQuery("select s from Spot s " +
                    " join s.spotAdmin sa" +
                    " where sa.id =: id", Spot.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e){
            return null;
        }

    }
}