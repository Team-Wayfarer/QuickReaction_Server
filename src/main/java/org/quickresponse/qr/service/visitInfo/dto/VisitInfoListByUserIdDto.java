package org.quickresponse.qr.service.visitInfo.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.spot.Spot;
import org.quickresponse.qr.domain.visitInfo.VisitInfo;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VisitInfoListByUserIdDto {

    private SpotDetailByVisitInfo spot;
    private LocalDateTime localDateTime;

    public VisitInfoListByUserIdDto(VisitInfo visitInfo) {
        spot = new SpotDetailByVisitInfo(visitInfo.getSpot());
        localDateTime = visitInfo.getLocalDateTime();
    }
}
