package com.leonardo.taskmanager.repositories;

import com.leonardo.taskmanager.model.Task;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer>{
    
}
