package org.quickresponse.qr.service.code.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SpotResponseDtoByCode {

    private Long id;

    @Builder
    public SpotResponseDtoByCode(Long id) {
        this.id = id;
    }
}
