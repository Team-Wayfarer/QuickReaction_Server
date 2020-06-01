package org.quickresponse.qr.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class VisitInfoRepository {

    private final EntityManager em;
}
