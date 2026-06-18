package com.maycool.access_control.service;

import com.maycool.access_control.repository.TemporaryAccessRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TemporaryAccessService {
    private final TemporaryAccessRepository temporaryAccessRepository;
}
