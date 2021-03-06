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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
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
    @NonNull
    private Status status;

    @ManyToOne
    @JoinColumn(nullable = true)
    @NonNull
    private Classification classification;

    @Enumerated(EnumType.STRING)
    @NonNull
    private Priority priority;

    @OneToMany(mappedBy = "task")
    private Set<Attachment> attachments;

    @ManyToOne
    @JoinColumn(nullable = false)
    @NonNull
    private Project project;

    @Column(nullable = true)
    @NonNull
    private LocalTime expectedDuration;

    @Column(nullable = false)
    @NonNull
    private LocalDateTime startedAt;

    @Column(nullable = true)
    @NonNull
    private LocalDateTime concludedAt;

    @Column(nullable = false, length = 100)
    @NonNull
    private String title;

    @Lob
    @Column(nullable = false)
    @NonNull
    private String description;

    @Column(nullable = false, length = 50)
    @NonNull
    private String requester;

    @OneToMany(mappedBy = "task")
    private Set<DetailedHour> detailedHours;

    @OneToMany(mappedBy = "task")
    private Set<Backlog> backlogs;

}
