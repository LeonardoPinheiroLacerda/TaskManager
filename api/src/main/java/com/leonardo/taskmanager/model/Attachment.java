package com.leonardo.taskmanager.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Builder
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"task"})

@Entity
@Table(name = "attachments")

public class Attachment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attachments_id_generator")
    @SequenceGenerator(name = "attachments_id_generator", sequenceName = "attachments_id_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Task task;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    private String size;

    @Column(nullable = false, length = 255)
    private String path;

    @Column(nullable = false)
    private LocalDateTime createdAt;

}
