package org.quickresponse.qr.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.dto.SpotSaveRequestDto;
import org.quickresponse.qr.repository.SpotRepository;
import org.quickresponse.qr.service.SpotService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class SpotApiController {
    private final SpotService spotService;
    private final SpotRepository spotRepository;

    @PostMapping("/api/domain/spots")
    public Long save(@RequestBody SpotSaveRequestDto spotSaveRequestDto){
       return spotService.join(spotSaveRequestDto);
    }





}
