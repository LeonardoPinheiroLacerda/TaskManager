package com.leonardo.taskmanager.repositories;

import com.leonardo.taskmanager.model.Team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer>{
    
}
