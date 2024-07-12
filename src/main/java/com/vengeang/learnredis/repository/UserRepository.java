package com.vengeang.learnredis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vengeang.learnredis.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByApiKey(String apiKey);
}
