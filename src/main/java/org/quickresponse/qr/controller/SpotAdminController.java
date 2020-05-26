package org.quickresponse.qr.controller;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.dto.SpotAdminSaveRequestDto;
import org.quickresponse.qr.dto.SpotAdminSaveResponseDto;
import org.quickresponse.qr.service.SpotAdminService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cfcqr/api/spotAdmins")
public class SpotAdminController {

    private final SpotAdminService spotAdminService;

    @PostMapping("/")
    public SpotAdminSaveResponseDto join(@RequestBody SpotAdminSaveRequestDto spotAdminSaveRequestDto){
        Long id = spotAdminService.join(spotAdminSaveRequestDto);
        return new SpotAdminSaveResponseDto(id);

    }
}
