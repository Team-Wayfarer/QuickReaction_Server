package org.quickresponse.qr.service.spot.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.visitInfo.VisitInfo;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SpotVisitInfoDetailsDto {

    private Long id;
    private String username;

    public SpotVisitInfoDetailsDto(VisitInfo visitInfo) {
        this.id = visitInfo.getId();
        this.username = visitInfo.getUser().getName();
    }
}
