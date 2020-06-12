package org.quickresponse.qr.service.center;

import lombok.RequiredArgsConstructor;
import org.quickresponse.qr.domain.center.Center;
import org.quickresponse.qr.domain.center.CenterRepository;
import org.quickresponse.qr.service.center.dto.CenterLoginRequestDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CenterService {

    private final CenterRepository centerRepository;

    public Center login(CenterLoginRequestDto dto) {
        return centerRepository.login(dto);
    }
}
