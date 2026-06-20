package com.maycool.access_control.controller;

import com.maycool.access_control.entity.Turnstile;
import com.maycool.access_control.service.TurnstileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/turnstile")
public class TurnstileController {

    private final TurnstileService turnstileService;

    @GetMapping
    public ResponseEntity<List<Turnstile>> allList(){
        List<Turnstile> allList = turnstileService.allList();
        return ResponseEntity.ok(allList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Turnstile> srcById(@PathVariable Long id){
         return turnstileService.srcById(id)
                 .map(ResponseEntity::ok)
                 .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Turnstile> create (@RequestBody Turnstile newTurnstile){
        Turnstile turnstileSave = turnstileService.save(newTurnstile);
        return ResponseEntity.status(201).body(turnstileSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turnstile> update (@PathVariable Long id, @RequestBody Turnstile updateData ){
            Turnstile turnstileUpdate = turnstileService.update(id, updateData);
            return ResponseEntity.ok(turnstileUpdate);
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> toggleStatus(@PathVariable Long id){
        turnstileService.toggleStatus(id);
        return ResponseEntity.noContent().build();
    }

}
