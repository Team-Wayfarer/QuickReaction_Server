package org.quickresponse.qr.service.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.user.User;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDetailResponseDto {

    private Long id;
    private String name;
    private String contact;
    private String email;

    @Builder
    public UserDetailResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.contact = user.getContact();
        this.email = user.getEmail();
    }
}
