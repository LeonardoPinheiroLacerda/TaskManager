package com.leonardo.taskmanager.repositories;

import com.leonardo.taskmanager.model.Attachment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer>{
    
}
