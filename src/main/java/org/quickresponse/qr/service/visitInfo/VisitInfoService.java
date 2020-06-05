package org.quickresponse.qr.service.visitInfo;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.spot.Spot;
import org.quickresponse.qr.domain.user.User;
import org.quickresponse.qr.domain.visitInfo.VisitInfo;
import org.quickresponse.qr.domain.spot.SpotRepository;
import org.quickresponse.qr.domain.user.UserRepository;
import org.quickresponse.qr.domain.visitInfo.VisitInfoRepository;
import org.quickresponse.qr.service.visitInfo.dto.VisitInfoListByUserIdDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        if(user==null) {
            throw new NoResultException("입력한 userId가 존재하지 않습니다. ");
        }

        Spot spot = spotRepository.findOne(spotId);
        if(spot==null) {
            throw new NoResultException("입력한 spotId가 존재하지 않습니다. ");
        }

        VisitInfo visitInfo =  VisitInfo.builder()
                                .user(user)
                                .spot(spot)
                                .localDateTime(LocalDateTime.now())
                                .build();
        VisitInfo saved = visitInfoRepository.save(visitInfo);
        return saved.getId();
    }

    public List<VisitInfoListByUserIdDto> findVisitInfoListByUserId(Long id, int offset, int limit) {
        User user = userRepository.findById(id);
        if(user==null)
            throw new IllegalStateException("들록된 회원이 없습니다.");

        List<VisitInfo> visitInfos = visitInfoRepository.findVisitInfoListByUserId(id, offset, limit);

        List<VisitInfoListByUserIdDto> collect = visitInfos.stream()
                .map(v -> new VisitInfoListByUserIdDto(v))
                .collect(Collectors.toList());

        return collect;
    }
}
