package org.quickresponse.qr.controller;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.Spot;
import org.quickresponse.qr.dto.SpotFindAllRequestDto;
import org.quickresponse.qr.dto.SpotFindOneRequestDto;
import org.quickresponse.qr.dto.SpotSaveRequestDto;
import org.quickresponse.qr.dto.SpotSaveResponseDto;
import org.quickresponse.qr.repository.SpotRepository;
import org.quickresponse.qr.service.SpotService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/cfcqr/api")
public class SpotController {

    private final SpotService spotService;
    private final SpotRepository spotRepository;

    @PostMapping("/spots")                                       //새로운 spot 생성
    public SpotSaveResponseDto save(@RequestBody SpotSaveRequestDto spotSaveRequestDto){
        Long joinId = spotService.join(spotSaveRequestDto);
        return new SpotSaveResponseDto(joinId);
    }


    @GetMapping("/spots")                                                    //모든 spot 확인
    public List<SpotFindAllRequestDto> findAllSpot(){
        List<Spot> spots = spotRepository.findAll();
        if(spots.isEmpty())
            throw new IllegalStateException("현재 등록된 장소가 없습니다. ");

        return spots.stream()
                .map(s -> new SpotFindAllRequestDto(s))
                .collect(Collectors.toList());
    }

    @GetMapping("/spots/{spotId}")                                              //id로 spot의 vistinfo 조회
    public List<SpotFindOneRequestDto> findOneVisitInfo(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                                @RequestParam(value = "limit", defaultValue = "100") int limit,
                                                @PathVariable("spotId") Long id){
        List<Spot> spotDetails = spotRepository.findOneDetails(id, offset, limit);
        return spotDetails.stream()
                .map(m -> new SpotFindOneRequestDto(m))
                .collect(Collectors.toList());
    }







}
