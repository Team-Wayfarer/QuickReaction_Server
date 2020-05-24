package org.quickresponse.qr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quickresponse.qr.domain.User;
import org.quickresponse.qr.dto.UserLoginDto;
import org.quickresponse.qr.dto.UserSaveRequestDto;
import org.quickresponse.qr.dto.UserUpdateRequestDto;
import org.quickresponse.qr.dto.VisitInfoDto;
import org.quickresponse.qr.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public Long join(@RequestBody UserSaveRequestDto dto) {
        User user = userService.join(dto);
        return user.getId();
    }

    @GetMapping
    public long login(@RequestBody UserLoginDto dto) {
        return userService.login(dto);
    }

    @GetMapping("/{id}/visit")
    public List<VisitInfoDto> getVisitInfoList(@PathVariable Long id) {
        return userService.getVisitInfoList(id);
    }

    @GetMapping("/{id}")
    public User getUserInfo(@PathVariable Long id) {
        return userService.getUserInfo(id);
    }

    @PutMapping("/{id}")
    public Long updateContact(@PathVariable Long id, @RequestBody UserUpdateRequestDto dto) {
        User user = userService.updateContact(id, dto);
        System.out.println(dto.getContact());
        return user.getId();
    }
}
