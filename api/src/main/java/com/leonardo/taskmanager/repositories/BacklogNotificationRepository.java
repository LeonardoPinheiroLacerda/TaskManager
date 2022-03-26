package com.leonardo.taskmanager.repositories;

import com.leonardo.taskmanager.model.BacklogNotification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogNotificationRepository extends JpaRepository<BacklogNotification, Integer>{
    
}
