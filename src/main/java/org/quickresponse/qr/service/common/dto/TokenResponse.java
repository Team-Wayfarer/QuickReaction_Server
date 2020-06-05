package org.quickresponse.qr.service.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenResponse {

    private String accessToken;
    private String tokenType;
    private Long id;
}
