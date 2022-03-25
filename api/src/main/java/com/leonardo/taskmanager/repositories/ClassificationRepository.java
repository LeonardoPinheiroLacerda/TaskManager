package com.leonardo.taskmanager.repositories;

import com.leonardo.taskmanager.model.Classification;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassificationRepository extends JpaRepository<Classification, Integer>{
    
}
