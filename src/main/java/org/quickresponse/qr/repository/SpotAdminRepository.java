package org.quickresponse.qr.repository;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.SpotAdmin;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SpotAdminRepository {

    private final EntityManager em;

    public Long join(SpotAdmin spotAdmin) {
        if(!findByBusinessName(spotAdmin.getBusinessNumber()).isEmpty()) {
            throw new InvalidDataAccessApiUsageException("이미 등록된 사업자 번호와 동일합니다.");
        }

        em.persist(spotAdmin);
        return spotAdmin.getId();
    }

    private List<SpotAdmin> findByBusinessName(String businessNumber) {
         return em.createQuery("select sa from SpotAdmin sa" +
                " where sa.businessNumber =:businessNumber ", SpotAdmin.class)
                .setParameter("businessNumber", businessNumber)
                .getResultList();
    }


}
