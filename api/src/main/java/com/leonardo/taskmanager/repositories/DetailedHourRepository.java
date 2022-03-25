package com.leonardo.taskmanager.repositories;

import com.leonardo.taskmanager.model.DetailedHour;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailedHourRepository extends JpaRepository<DetailedHour, Integer>{
    
}
