package org.quickresponse.qr.controller;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.spotAdmin.SpotAdmin;
import org.quickresponse.qr.service.spotAdmin.SpotAdminService;
import org.quickresponse.qr.service.spotAdmin.dto.SpotAdminDetailResponseDto;
import org.quickresponse.qr.service.spotAdmin.dto.SpotAdminFindAllResponseDto;
import org.quickresponse.qr.service.spotAdmin.dto.SpotAdminSaveRequestDto;
import org.quickresponse.qr.service.spotAdmin.dto.SpotAdminSaveResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/")
    public List<SpotAdminFindAllResponseDto> findAllSpotAdmin(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                                              @RequestParam(value = "limit", defaultValue = "100") int limit){
        List<SpotAdmin> all = spotAdminService.findAll(offset, limit);
        List<SpotAdminFindAllResponseDto> collect = all.stream()
                .map(s -> new SpotAdminFindAllResponseDto(s))
                .collect(Collectors.toList());
        return collect;
    }

    @GetMapping("/{spotAdminId}")
    public SpotAdminDetailResponseDto findOneDetail(@PathVariable("spotAdminId") Long id){
        return new SpotAdminDetailResponseDto(spotAdminService.findOneDetail(id));
    }
}
