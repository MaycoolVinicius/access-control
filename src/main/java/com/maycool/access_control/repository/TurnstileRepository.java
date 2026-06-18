package com.maycool.access_control.repository;

import com.maycool.access_control.entity.Turnstile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnstileRepository extends JpaRepository<Turnstile, Long> {
}
