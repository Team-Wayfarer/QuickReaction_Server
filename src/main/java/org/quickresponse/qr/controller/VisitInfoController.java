package org.quickresponse.qr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cfcqr/api/visitInfos")
public class VisitInfoController {

    private final VisitInfoController visitInfoController;


}
