package org.quickresponse.qr.domain.spotAdmin;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.spotAdmin.SpotAdmin;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SpotAdminRepository {

    private final EntityManager em;

    public Long join(SpotAdmin spotAdmin) {
        if (!findByBusinessName(spotAdmin.getBusinessNumber()).isEmpty()) {
            throw new InvalidDataAccessApiUsageException("이미 등록된 사업자 번호와 동일합니다.");
        }
        em.persist(spotAdmin);
        return spotAdmin.getId();
    }

    public SpotAdmin find(Long id) {
        return em.find(SpotAdmin.class, id);
    }

    public List<SpotAdmin> findByBusinessName(String businessNumber) {
        return em.createQuery("select sa from SpotAdmin sa" +
                " where sa.businessNumber =:businessNumber ", SpotAdmin.class)
                .setParameter("businessNumber", businessNumber)
                .getResultList();
    }

    public SpotAdmin findOneDetail(Long id) {
        if (find(id) == null) {
            throw new InvalidDataAccessApiUsageException("존재하지 않는 spotAdmin입니다.");
        }
        try {
            return em.createQuery("select sa from SpotAdmin sa" +
                    " join fetch sa.spot s " +
                    " where sa.id =:id", SpotAdmin.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }catch (NoResultException e){
            throw new NoResultException("등록된 spot이 없습니다.");
        }
    }

    public List<SpotAdmin> findAll(int offset, int limit) {
        return em.createQuery("select sa from SpotAdmin sa" +
                " join fetch sa.spot s",
                SpotAdmin.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
