package com.vengeang.learnredis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vengeang.learnredis.entity.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial,Long>{
    
}
