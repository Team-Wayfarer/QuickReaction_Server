package org.quickresponse.qr.controller;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.Spot;
import org.quickresponse.qr.dto.SpotFindAllReponseDto;
import org.quickresponse.qr.dto.SpotFindOneResponseDto;
import org.quickresponse.qr.dto.SpotSaveRequestDto;
import org.quickresponse.qr.dto.SpotSaveResponseDto;
import org.quickresponse.qr.repository.SpotRepository;
import org.quickresponse.qr.service.SpotService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/cfcqr/api/spots")
public class SpotController {

    private final SpotService spotService;
    private final SpotRepository spotRepository;

    @PostMapping("/")                                       //새로운 spot 생성
    public SpotSaveResponseDto save(@RequestBody SpotSaveRequestDto spotSaveRequestDto){
        Long joinId = spotService.join(spotSaveRequestDto);
        return new SpotSaveResponseDto(joinId);
    }

    @GetMapping("/")                                                    //모든 spot 확인
    public List<SpotFindAllReponseDto> findAllSpot(){
        List<Spot> spots = spotRepository.findAll();
        if(spots.isEmpty()) {
            throw new IllegalStateException("현재 등록된 장소가 없습니다. ");
        }

        return spots.stream()
                .map(s -> new SpotFindAllReponseDto(s))
                .collect(Collectors.toList());
    }

    @GetMapping("/{spotId}")                                              //id로 spot의 vistinfo 조회
    public List<SpotFindOneResponseDto> findOneVisitInfo(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                                         @RequestParam(value = "limit", defaultValue = "100") int limit,
                                                         @PathVariable("spotId") Long id){
        List<Spot> spotDetails = spotRepository.findOneDetails(id, offset, limit);
        return spotDetails.stream()
                .map(m -> new SpotFindOneResponseDto(m))
                .collect(Collectors.toList());
    }
}
