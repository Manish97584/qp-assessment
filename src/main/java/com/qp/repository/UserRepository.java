package com.qp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qp.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<String> findByEmail(String email);
}
