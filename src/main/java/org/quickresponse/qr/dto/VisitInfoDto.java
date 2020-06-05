package org.quickresponse.qr.dto;

import lombok.Builder;
import org.quickresponse.qr.domain.Spot;
import org.quickresponse.qr.domain.VisitInfo;

import java.time.LocalDateTime;

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
