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

            SpotAdmin spotAdmin =  SpotAdmin.builder()
                    .name("사업자1")
                    .password("1212")
                    .email("xxx@naver.com")
                    .businessNumber("11111111")
                    .contact("010-0000-0000")
                    .build();
            em.persist(spotAdmin);

            SpotAdmin spotAdmin2 =  SpotAdmin.builder()
                    .name("사업자2")
                    .password("3434")
                    .email("aaa@gmail.com")
                    .businessNumber("22222222")
                    .contact("010-1111-1111")
                    .build();
            em.persist(spotAdmin2);


            Address address =  Address.builder()
                    .city("인천시")
                    .gunGu("남동구")
                    .zipcode("이노프라자")
                    .detail("7층")
                    .build();

            Spot spot = Spot.builder()
                    .name("코다차야")
                    .address(address)
                    .lat("37.4457671")
                    .lng("126.699528")
                    .spotAdmin(spotAdmin)
                    .build();
            em.persist(spot);

            Address address2 = Address.builder()
                    .city("인천시")
                    .gunGu("남동구")
                    .zipcode("롯데백화점")
                    .detail("지하1")
                    .build();

            Spot spot2 = Spot.builder()
                    .name("프리스비")
                    .address(address2)
                    .lat("37.4424881")
                    .lng("126.7001883")
                    .spotAdmin(spotAdmin2)
                    .build();
            em.persist(spot2);

            Code code =  Code.builder()
                    .spot(spot)
                    .url("urlA")
                    .build();
            em.persist(code);

            Code code2 =  Code.builder()
                    .spot(spot2)
                    .url("urlB")
                    .build();
            em.persist(code2);

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
