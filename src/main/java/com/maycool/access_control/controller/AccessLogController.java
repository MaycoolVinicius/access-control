package com.maycool.access_control.controller;

import com.maycool.access_control.service.AccessLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/access-log")
public class AccessLogController {
    private final AccessLogService accessLogService;



}
