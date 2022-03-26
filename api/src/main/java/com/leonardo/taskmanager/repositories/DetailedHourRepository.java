package com.leonardo.taskmanager.repositories;

import com.leonardo.taskmanager.model.DetailedHour;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailedHourRepository extends JpaRepository<DetailedHour, Integer>{
    
}
