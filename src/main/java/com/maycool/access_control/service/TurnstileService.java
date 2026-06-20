package com.maycool.access_control.service;

import com.maycool.access_control.entity.Turnstile;
import com.maycool.access_control.repository.TurnstileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TurnstileService {
    private final TurnstileRepository turnstileRepository;

    public List<Turnstile> allList() {
        return turnstileRepository.findAll();
    }

    public Optional<Turnstile> srcById(Long id){
        return turnstileRepository.findById(id);

    }

    public Turnstile save(Turnstile newTurnstile) {
        return turnstileRepository.save(newTurnstile);
    }
    public Turnstile update(Long id, Turnstile updateData){
        Turnstile turnstileUpdate = turnstileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catraca não encontrada"));
                turnstileUpdate.setLocation(updateData.getLocation());
        return turnstileRepository.save(turnstileUpdate);
    }

    public void toggleStatus(Long id) {
        Turnstile turnstile = turnstileRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Catraca não encontrada"));
                turnstile.setStatus(!turnstile.isStatus());
                turnstileRepository.save(turnstile);
    }
}
