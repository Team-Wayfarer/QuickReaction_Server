package org.quickresponse.qr.service.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.user.User;
import org.quickresponse.qr.domain.user.UserStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangeInfectionStatusResponseDto {

    private Long userId;
    private UserStatus userStatus;

    public ChangeInfectionStatusResponseDto(User user) {
        userId = user.getId();
        userStatus = user.getUserStatus();
    }
}
