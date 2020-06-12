package org.quickresponse.qr.service.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.user.User;
import org.quickresponse.qr.domain.user.UserStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangeStatusResponseDto {

    private Long userId;
    private UserStatus userStatus;

    public ChangeStatusResponseDto(User user) {
        userId = user.getId();
        userStatus = user.getUserStatus();
    }
}
