package org.quickresponse.qr;


import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;


/**
 * User
 *- userA, userB
 *
 * Spot
 * spotA, spotB
 *
 */
@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final EntityManager em;

        public void dbInit1(){
            User user = new User().builder()
                    .name("userA")
                    .contact("000-0000-0000")
                    .build();
            em.persist(user);

            User user2 = new User().builder()
                    .name("userB")
                    .contact("111-1111-1111")
                    .build();
            em.persist(user2);


            Code code = new Code().builder()
                    .url("urlA")
                    .build();
            em.persist(code);

            Code code2 = new Code().builder()
                    .url("urlB")
                    .build();
            em.persist(code2);

            StoreAdmin storeAdmin = new StoreAdmin().builder()
                    .name("사업자1")
                    .businessNumber("11111111")
                    .build();
            em.persist(storeAdmin);

            StoreAdmin storeAdmin2 = new StoreAdmin().builder()
                    .name("사업자2")
                    .businessNumber("22222222")
                    .build();
            em.persist(storeAdmin2);



            Spot spot = new Spot().builder()
                    .name("spotA")
                    .address("addressA")
                    .lat("latA")
                    .lng("lngA")
                    .storeAdmin(storeAdmin)
                    .code(code)
                    .build();
            em.persist(spot);

            Spot spot2 = new Spot().builder()
                    .name("spotB")
                    .address("addressB")
                    .lat("latB")
                    .lng("lngB")
                    .storeAdmin(storeAdmin2)
                    .code(code2)
                    .build();
            em.persist(spot2);

           VisitInfo visitInfo = new VisitInfo().builder()
                   .user(user)
                   .spot(spot)
                   .build();
            em.persist(visitInfo);

            VisitInfo visitInfo2 = new VisitInfo().builder()
                    .user(user)
                    .spot(spot2)
                    .build();
            em.persist(visitInfo2);

            VisitInfo visitInfo3 = new VisitInfo().builder()
                    .user(user2)
                    .spot(spot2)
                    .build();
            em.persist(visitInfo3);


        }

    }
}
