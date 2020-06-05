package org.quickresponse.qr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quickresponse.qr.service.common.dto.TokenResponse;
<<<<<<< HEAD:src/main/java/org/quickresponse/qr/controller/LoginController.java
import org.quickresponse.qr.service.spotAdmin.SpotAdminService;
import org.quickresponse.qr.service.spotAdmin.dto.SpotAdminLoginDto;
import org.quickresponse.qr.service.user.user.UserLoginDto;
=======
import org.quickresponse.qr.service.user.dto.UserLoginDto;
>>>>>>> 0a88a2c0c989b04ec2babc9180b4d75a447a1a63:src/main/java/org/quickresponse/qr/controller/LoginUserController.java
import org.quickresponse.qr.service.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cfcqr/api/login")
public class LoginUserController {

    private final UserService userService;
    private final SpotAdminService spotAdminService;

    @PostMapping("/user")
    public TokenResponse login(@RequestBody UserLoginDto dto) {
        String token = userService.createToken(dto);
        return new TokenResponse(token, "bearer");
    }

    @PostMapping("/spotAdmin")
    public TokenResponse loginSpotAdmin(@RequestBody SpotAdminLoginDto spotAdminLoginDto){
        String token = spotAdminService.createToken(spotAdminLoginDto);
        return new TokenResponse(token, "bearer");
    }
}
