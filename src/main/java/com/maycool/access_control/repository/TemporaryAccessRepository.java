package com.maycool.access_control.repository;

import com.maycool.access_control.entity.TemporaryAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemporaryAccessRepository extends JpaRepository<TemporaryAccess, Long> {
}
