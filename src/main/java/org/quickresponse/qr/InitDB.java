package org.quickresponse.qr;


import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.*;
import org.quickresponse.qr.domain.code.Code;
import org.quickresponse.qr.domain.spot.Spot;
import org.quickresponse.qr.domain.spotAdmin.SpotAdmin;
import org.quickresponse.qr.domain.user.User;
import org.quickresponse.qr.domain.user.UserStatus;
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
        initService.dbInit2();
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
                    .duid("a")
                    .build();
            em.persist(user);

            User user2 =  User.builder()
                    .name("userB")
                    .contact("111-1111-1111")
                    .email("bbb@aaa.com")
                    .password("abcd")
                    .duid("b")
                    .build();
            em.persist(user2);

            User user3 =  User.builder()
                    .name("userC")
                    .contact("22-2222-2222")
                    .email("123123@aaa.com")
                    .password("cccc")
                    .build();
            em.persist(user3);

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

            VisitInfo visitInfo3 = VisitInfo.builder()
                    .user(user2)
                    .spot(spot2)
                    .localDateTime(LocalDateTime.now())
                    .build();
            em.persist(visitInfo3);

            VisitInfo visitInfo4 = VisitInfo.builder()
                    .user(user3)
                    .spot(spot)
                    .localDateTime(LocalDateTime.now())
                    .build();
            em.persist(visitInfo4);

            VisitInfo visitInfo5 = VisitInfo.builder()
                    .user(user3)
                    .spot(spot2)
                    .localDateTime(LocalDateTime.now())
                    .build();
            em.persist(visitInfo5);
        }

        public void dbInit2() {

            User user = User.builder()
                    .name("userD")
                    .contact("444-4444-4444")
                    .email("zzzz@zzzz.com")
                    .password("abcd")
                    .duid("d")
                    .build();
            em.persist(user);

            User user2 =  User.builder()
                    .name("userE")
                    .contact("5555-5555-5555")
                    .email("xxx@xxxx.com")
                    .password("abcd")
                    .duid("e")
                    .build();
            em.persist(user2);

            User user3 =  User.builder()
                    .name("userF")
                    .contact("777-7777-7777")
                    .email("ccc@ccc.com")
                    .password("cccc")
                    .duid("f")
                    .build();
            em.persist(user3);

            SpotAdmin spotAdmin =  SpotAdmin.builder()
                    .name("사업자3")
                    .password("1212")
                    .email("tttt@naver.com")
                    .businessNumber("070707070")
                    .contact("010-0707-0707")
                    .build();
            em.persist(spotAdmin);

            SpotAdmin spotAdmin2 =  SpotAdmin.builder()
                    .name("사업자4")
                    .password("3434")
                    .email("rrrr@gmail.com")
                    .businessNumber("34343434")
                    .contact("010-2424-2424")
                    .build();
            em.persist(spotAdmin2);


            Address address =  Address.builder()
                    .city("창원시")
                    .gunGu("창원구")
                    .zipcode("창원프라자")
                    .detail("7층")
                    .build();

            Spot spot = Spot.builder()
                    .name("창원차야")
                    .address(address)
                    .lat("37.4457671")
                    .lng("126.699528")
                    .spotAdmin(spotAdmin)
                    .build();
            em.persist(spot);

            Address address2 = Address.builder()
                    .city("영덕군")
                    .gunGu("영덕군")
                    .zipcode("영덕백화점")
                    .detail("지하1")
                    .build();

            Spot spot2 = Spot.builder()
                    .name("영덕스비")
                    .address(address2)
                    .lat("37.4424881")
                    .lng("126.7001883")
                    .spotAdmin(spotAdmin2)
                    .build();
            em.persist(spot2);

            Code code =  Code.builder()
                    .spot(spot)
                    .url("urlC")
                    .build();
            em.persist(code);

            Code code2 =  Code.builder()
                    .spot(spot2)
                    .url("urlD")
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

            VisitInfo visitInfo4 = VisitInfo.builder()
                    .user(user3)
                    .spot(spot)
                    .localDateTime(LocalDateTime.now())
                    .build();
            em.persist(visitInfo4);

            VisitInfo visitInfo5 = VisitInfo.builder()
                    .user(user3)
                    .spot(spot2)
                    .localDateTime(LocalDateTime.now())
                    .build();
            em.persist(visitInfo5);
        }
    }
}
