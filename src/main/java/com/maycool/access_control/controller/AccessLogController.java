package com.maycool.access_control.controller;

import com.maycool.access_control.entity.AccessLog;
import com.maycool.access_control.service.AccessLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/access-log")
public class AccessLogController {
    private final AccessLogService accessLogService;

    @GetMapping
    ResponseEntity<List<AccessLog>> allList() {
        List<AccessLog> allList = accessLogService.allList();
        return ResponseEntity.ok(allList);
    }

    @PostMapping
    ResponseEntity<AccessLog> register(@RequestBody AccessLog newLog) {
        AccessLog logSave = accessLogService.save(newLog);
        return ResponseEntity.status(201).body(logSave);
    }

}
