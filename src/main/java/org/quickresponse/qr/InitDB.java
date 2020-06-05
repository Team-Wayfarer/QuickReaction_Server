package org.quickresponse.qr;


import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.*;
import org.quickresponse.qr.domain.code.Code;
import org.quickresponse.qr.domain.spot.Spot;
import org.quickresponse.qr.domain.spotAdmin.SpotAdmin;
import org.quickresponse.qr.domain.user.User;
import org.quickresponse.qr.domain.visitInfo.VisitInfo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;


/*
*
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

        public void dbInit1() {

            User user = User.builder()
                    .name("userA")
                    .contact("000-0000-0000")
                    .email("aaa@aaa.com")
                    .password("abcd")
                    .build();
            em.persist(user);

            User user2 =  User.builder()
                    .name("userB")
                    .contact("111-1111-1111")
                    .email("bbb@aaa.com")
                    .password("abcd")
                    .build();
            em.persist(user2);


            Code code =  Code.builder()
                    .url("urlA")
                    .build();
            em.persist(code);

            Code code2 =  Code.builder()
                    .url("urlB")
                    .build();
            em.persist(code2);


            SpotAdmin spotAdmin =  SpotAdmin.builder()
                    .name("사업자1")
                    .businessNumber("11111111")
                    .contact("010-0000-0000")
                    .build();
            em.persist(spotAdmin);

            SpotAdmin spotAdmin2 =  SpotAdmin.builder()
                    .name("사업자2")
                    .businessNumber("22222222")
                    .contact("010-1111-1111")
                    .build();
            em.persist(spotAdmin2);


            Address address =  Address.builder()
                    .city("인천시")
                    .gunGu("부평구")
                    .zipcode("재성아파트")
                    .detail("13동 1313호")
                    .build();

            Spot spot = Spot.builder()
                    .name("spotA")
                    .address(address)
                    .lat("latA")
                    .lng("lngA")
                    .spotAdmin(spotAdmin)
                    .code(code)
                    .build();
            em.persist(spot);

            Address address2 = Address.builder()
                    .city("서울시")
                    .gunGu("강남구 ")
                    .zipcode("명직아파트")
                    .detail("18동 1818호")
                    .build();

            Spot spot2 = Spot.builder()
                    .name("spotB")
                    .address(address2)
                    .lat("latB")
                    .lng("lngB")
                    .spotAdmin(spotAdmin2)
                    .code(code2)
                    .build();
            em.persist(spot2);


            VisitInfo visitInfo =  VisitInfo.builder()
                    .user(user)
                    .spot(spot)
                    .localDateTime(LocalDateTime.now())
                    .build();
            em.persist(visitInfo);

            VisitInfo visitInfo2 =  VisitInfo.builder()
                    .user(user)
                    .spot(spot2)
                    .localDateTime(LocalDateTime.now())
                    .build();
            em.persist(visitInfo2);

            VisitInfo visitInfo3 = VisitInfo.builder()
                    .user(user2)
                    .spot(spot2)
                    .localDateTime(LocalDateTime.now())
                    .build();
            em.persist(visitInfo3);
        }
    }
}
