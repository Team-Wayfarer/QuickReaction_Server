package org.quickresponse.qr.domain.code;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.spot.Spot;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CodeRepository {

    private final EntityManager em;

    public Code findById(Long id) {
        return em.find(Code.class, id);
    }

    public Code save(Spot spot, String url) {
        Code code = new Code(spot, url);
        em.persist(code);
        return code;
    }
}
