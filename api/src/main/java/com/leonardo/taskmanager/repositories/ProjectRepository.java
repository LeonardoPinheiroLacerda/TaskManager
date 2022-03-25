package com.leonardo.taskmanager.repositories;

import com.leonardo.taskmanager.model.Project;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer>{
    
}
