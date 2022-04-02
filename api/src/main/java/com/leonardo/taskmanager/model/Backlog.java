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
    @NonNull
    private String note;

    @Column(nullable = false)
    @NonNull
    private LocalDateTime createdAt;

    @ManyToOne
    @NonNull
    private Task task;

    @ManyToOne
    @NonNull
    private User user;

    @OneToMany(mappedBy = "backlog")
    private Set<BacklogNotification> notifications;

}
