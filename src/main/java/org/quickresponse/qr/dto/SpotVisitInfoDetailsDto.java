package org.quickresponse.qr.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.Spot;
import org.quickresponse.qr.domain.User;
import org.quickresponse.qr.domain.VisitInfo;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SpotVisitInfoDetailsDto {
    private Long id;
    private String username;


    public SpotVisitInfoDetailsDto(VisitInfo visitInfo) {
        this.id = visitInfo.getId();
        this.username = visitInfo.getUser().getName();
    }
}
