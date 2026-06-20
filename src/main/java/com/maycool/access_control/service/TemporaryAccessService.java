package com.maycool.access_control.service;

import com.maycool.access_control.entity.TemporaryAccess;
import com.maycool.access_control.repository.TemporaryAccessRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemporaryAccessService {
    private final TemporaryAccessRepository temporaryAccessRepository;


    public List<TemporaryAccess> allList() {
        return temporaryAccessRepository.findAll();
    }


    public TemporaryAccess save(TemporaryAccess newVisitor) {
        return temporaryAccessRepository.save(newVisitor);
    }

    public void status(Long id) {
        TemporaryAccess temporaryAccess = temporaryAccessRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visitante não encontrado"));
        temporaryAccess.setActive(!temporaryAccess.isActive());
        temporaryAccessRepository.save(temporaryAccess);
    }

}



