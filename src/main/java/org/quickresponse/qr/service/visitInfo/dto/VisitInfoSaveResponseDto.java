package org.quickresponse.qr.service.visitInfo.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VisitInfoSaveResponseDto {

    private Long visitInfo_Id;

    public VisitInfoSaveResponseDto(Long id) {
        this.visitInfo_Id = id;
    }
}
