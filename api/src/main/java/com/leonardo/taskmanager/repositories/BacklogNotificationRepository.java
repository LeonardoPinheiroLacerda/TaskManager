package com.leonardo.taskmanager.repositories;

import com.leonardo.taskmanager.model.BacklogNotification;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BacklogNotificationRepository extends JpaRepository<BacklogNotification, Integer>{
    
}
