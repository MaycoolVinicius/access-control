package com.maycool.access_control.service;

import com.maycool.access_control.entity.User;
import com.maycool.access_control.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> allList() {
        return userRepository.findAll();
    }

    public Optional<User> srcById(Long id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(Long id, User updatedData) {
        User userUpdate = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        userUpdate.setName(updatedData.getName());
        return userRepository.save(userUpdate);
    }


    public void replace(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
                user.setStatus(!user.isStatus());
                userRepository.save(user);
    }
}

