package com.leonardo.taskmanager.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Builder
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"task", "user"})

@Entity
@Table(name = "backlogs")
public class Backlog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "backlogs_id_generator")
    @SequenceGenerator(name = "backlogs_id_generator", sequenceName = "backlogs_id_seq", allocationSize = 1)
    private Integer id;

    @Column(nullable = false)
    @Lob
    private String note;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    private Task task;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "backlog")
    private Set<BacklogNotification> notifications;

}
