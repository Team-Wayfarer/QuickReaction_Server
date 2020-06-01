package org.quickresponse.qr.service;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.Spot;
import org.quickresponse.qr.domain.User;
import org.quickresponse.qr.domain.VisitInfo;
import org.quickresponse.qr.repository.SpotRepository;
import org.quickresponse.qr.repository.UserRepository;
import org.quickresponse.qr.repository.VisitInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VisitInfoService {

    private final VisitInfoRepository visitInfoRepository;
    private final SpotRepository spotRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long save(Long userId, Long spotId) {
        User user = userRepository.findById(userId);
        if(user==null)
            throw new NoResultException("입력한 userId가 존재하지 않습니다. ");

        Spot spot = spotRepository.findOne(spotId);
        if(spot==null)
            throw new NoResultException("입력한 spotId가 존재하지 않습니다. ");

        VisitInfo visitInfo =  VisitInfo.builder()
                                .user(user)
                                .spot(spot)
                                .localDateTime(LocalDateTime.now())
                                .build();
        VisitInfo saved = visitInfoRepository.save(visitInfo);
        return saved.getId();
    }
}
