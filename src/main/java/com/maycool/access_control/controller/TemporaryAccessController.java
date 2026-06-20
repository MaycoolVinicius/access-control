package com.maycool.access_control.controller;

import com.maycool.access_control.entity.TemporaryAccess;
import com.maycool.access_control.service.TemporaryAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/temporary-access")
public class TemporaryAccessController {
    private final TemporaryAccessService temporaryAccessService;

    @GetMapping
    ResponseEntity<List<TemporaryAccess>> allList(){
        List <TemporaryAccess> allList = temporaryAccessService.allList();
        return ResponseEntity.ok(allList);
    }

    @PostMapping
    ResponseEntity<TemporaryAccess> register(@RequestBody TemporaryAccess newVisitor) {
        TemporaryAccess newSave = temporaryAccessService.save(newVisitor);
        return ResponseEntity.status(201).body(newSave);
    }

    @PatchMapping("/{id}/status")
    ResponseEntity<Void> status (@PathVariable Long id){
        temporaryAccessService.status(id);
        return ResponseEntity.noContent().build();
    }

}
