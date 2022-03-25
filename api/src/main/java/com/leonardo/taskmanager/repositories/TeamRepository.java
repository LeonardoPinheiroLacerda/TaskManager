package com.leonardo.taskmanager.repositories;

import com.leonardo.taskmanager.model.Team;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer>{
    
}
