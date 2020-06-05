package org.quickresponse.qr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quickresponse.qr.service.common.dto.TokenResponse;
import org.quickresponse.qr.service.spotAdmin.SpotAdminService;
import org.quickresponse.qr.service.spotAdmin.dto.SpotAdminLoginDto;
import org.quickresponse.qr.service.user.dto.UserLoginDto;
import org.quickresponse.qr.service.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cfcqr/api/login")
public class LoginController {

    private final UserService userService;
    private final SpotAdminService spotAdminService;

    @PostMapping("/user")
    public TokenResponse login(@RequestBody UserLoginDto dto) {
        String token = userService.createToken(dto);
        Long userId = userService.getUserId(dto.getEmail());
        return new TokenResponse(token, "bearer", userId);
    }

    @PostMapping("/spotAdmin")
    public TokenResponse loginSpotAdmin(@RequestBody SpotAdminLoginDto spotAdminLoginDto){
        String token = spotAdminService.createToken(spotAdminLoginDto);
        Long spotAdminId = spotAdminService.getSpotAdminId(spotAdminLoginDto.getEmail());
        return new TokenResponse(token, "bearer", spotAdminId);
    }
}
