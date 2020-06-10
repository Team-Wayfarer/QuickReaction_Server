package org.quickresponse.qr.service.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.user.User;
import org.quickresponse.qr.domain.user.UserStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StatusChangeResponseDto {

    private Long id;
    private UserStatus userStatus;

    public StatusChangeResponseDto(User user) {
        id = user.getId();
        userStatus = user.getUserStatus();
    }
}
