package com.maycool.access_control.controller;

import com.maycool.access_control.entity.User;
import com.maycool.access_control.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    // GET /usuarios
    @GetMapping
    public List<User> allList() {
        return userService.allList();
    }

    // GET /usuarios/{id}
    @GetMapping("/{id}")
    public ResponseEntity<User> srcById(@PathVariable Long id) {
        return userService.srcById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /usuarios
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User newUser) {
        User userSave = userService.save(newUser);
        return ResponseEntity.status(201).body(userSave); // 201 Created
    }

    // PUT /usuarios/{id}
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User updatedData) {
        User userUpdated = userService.update(id, updatedData);
        return ResponseEntity.ok(userUpdated);
    }


    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> replace(@PathVariable Long id) {
        userService.replace(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

}
