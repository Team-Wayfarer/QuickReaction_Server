package org.quickresponse.qr.service.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.user.User;
import org.quickresponse.qr.domain.user.UserStatus;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDetailResponseDto {

    private Long userId;
    private String name;
    private String contact;
    private String email;
    private UserStatus userStatus;
    private LocalDateTime whenChange;

    @Builder
    public UserDetailResponseDto(User user) {
        this.userId = user.getId();
        this.name = user.getName();
        this.contact = user.getContact();
        this.email = user.getEmail();
        this.userStatus = user.getUserStatus();
        this.whenChange = user.getWhenChange();
    }
}
