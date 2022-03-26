package com.leonardo.taskmanager.repositories;

import com.leonardo.taskmanager.model.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{
    
}
