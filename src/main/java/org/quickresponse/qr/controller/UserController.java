package org.quickresponse.qr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quickresponse.qr.domain.User;
import org.quickresponse.qr.dto.UserSaveRequestDto;
import org.quickresponse.qr.dto.UserUpdateRequestDto;
import org.quickresponse.qr.dto.VisitInfoDto;
import org.quickresponse.qr.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cfcqr/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public Long join(@RequestBody UserSaveRequestDto dto) {
        return userService.join(dto);
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
        return user.getId();
    }
}
