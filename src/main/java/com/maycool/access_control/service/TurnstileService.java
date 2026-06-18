package com.maycool.access_control.service;

import com.maycool.access_control.repository.TurnstileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TurnstileService {
    private final TurnstileRepository turnstileRepository;

}
