package com.leonardo.taskmanager.repositories;

import com.leonardo.taskmanager.model.Priority;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<Priority, Integer>{
    
}
