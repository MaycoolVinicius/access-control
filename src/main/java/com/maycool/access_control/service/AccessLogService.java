package com.maycool.access_control.service;

import com.maycool.access_control.entity.AccessLog;
import com.maycool.access_control.repository.AccessLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccessLogService {
    private final AccessLogRepository accessLogRepository;

    public List<AccessLog> allList() {
        return accessLogRepository.findAll();
    }

    public AccessLog save(AccessLog newLog) {
        return accessLogRepository.save(newLog);
    }

}
