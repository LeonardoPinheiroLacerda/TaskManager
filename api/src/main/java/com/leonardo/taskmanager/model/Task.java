package com.leonardo.taskmanager.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.leonardo.taskmanager.model.enums.Priority;
import com.leonardo.taskmanager.model.enums.Status;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Builder
@EqualsAndHashCode(of = "id")
@ToString()

@Entity
@Table(name = "tasks")
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_id_generator")
    @SequenceGenerator(name = "tasks_id_generator", sequenceName = "tasks_id_seq", allocationSize = 1)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(nullable = true)
    private Classification classification;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @OneToMany(mappedBy = "task")
    private Set<Attachment> attachments;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Project project;

    @Column(nullable = true)
    private LocalTime expectedDuration;

    @Column(nullable = true)
    private LocalTime currentDuration;

    @Column(nullable = false)
    private LocalDateTime startedAt;

    @Column(nullable = true)
    private LocalDateTime concludedAt;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    @Column(nullable = false)
    private String description;

    @Column(nullable = false, length = 50)
    private String requester;

    @OneToMany(mappedBy = "task")
    private Set<DetailedHour> detailedHours;

    @OneToMany(mappedBy = "task")
    private Set<Backlog> backlogs;

}
