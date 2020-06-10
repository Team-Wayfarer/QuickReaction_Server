package org.quickresponse.qr.service.visitInfo.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.spot.Spot;
import org.quickresponse.qr.domain.visitInfo.VisitInfo;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VisitInfoDto {

    private Spot spot;

    private LocalDateTime localDateTime;

    @Builder
    public VisitInfoDto(Spot spot, LocalDateTime localDateTime) {
        this.spot = spot;
        this.localDateTime = localDateTime;
    }

    public VisitInfo toEntity() {
        return VisitInfo.builder()
                .spot(this.spot)
                .localDateTime(this.localDateTime)
                .build();
    }
}
