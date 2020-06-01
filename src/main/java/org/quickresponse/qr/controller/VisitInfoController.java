package org.quickresponse.qr.controller;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.dto.VisitInfoSaveResponseDto;
import org.quickresponse.qr.service.VisitInfoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cfcqr/api/visitInfos")
public class VisitInfoController {

    private final VisitInfoService visitInfoService;

    @PostMapping("/{userId}/{spotId}")
    public VisitInfoSaveResponseDto save(@PathVariable("userId") Long userId, @PathVariable("spotId") Long spotId){
        Long visitInfoId = visitInfoService.save(userId, spotId);
        return new VisitInfoSaveResponseDto(visitInfoId);
    }


}
