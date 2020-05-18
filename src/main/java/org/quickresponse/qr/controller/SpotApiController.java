package org.quickresponse.qr.controller;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.Spot;
import org.quickresponse.qr.dto.SpotFindAllRequestDto;
import org.quickresponse.qr.dto.SpotFindOneDetailsRequestDto;
import org.quickresponse.qr.dto.SpotSaveRequestDto;
import org.quickresponse.qr.repository.SpotRepository;
import org.quickresponse.qr.service.SpotService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class SpotApiController {
    private final SpotService spotService;
    private final SpotRepository spotRepository;

    @PostMapping("/api/domain/spots")                                       //새로운 spot 생성
    public Long save(@RequestBody SpotSaveRequestDto spotSaveRequestDto){
       return spotService.join(spotSaveRequestDto);
    }

    @GetMapping("/api/domain/spots")                                                    //모든 spot 확인
    public List<SpotFindAllRequestDto> findAllSpot(){
        List<Spot> spots = spotRepository.findAll();
        if(spots.isEmpty())
            throw new IllegalStateException("현재 등록된 장소가 없습니다. ");

        return spots.stream()
                .map(s -> new SpotFindAllRequestDto(s))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/domain/spots/{id}")                                              //id로 spot의 vistinfo 조회
    public SpotFindOneDetailsRequestDto findOneDetails(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                                       @RequestParam(value = "limit", defaultValue = "100") int limit,
                                                        @PathVariable("id") Long id){
        List<Spot> spotDetails = spotRepository.findOneDetails(id, offset, limit);


    }







}
