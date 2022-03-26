package com.leonardo.taskmanager.repositories;

import com.leonardo.taskmanager.model.Backlog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends JpaRepository<Backlog, Integer>{
    
}
