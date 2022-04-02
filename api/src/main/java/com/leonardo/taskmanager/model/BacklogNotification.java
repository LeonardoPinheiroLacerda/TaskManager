package com.leonardo.taskmanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@ToString(exclude = {"backlog", "notifiedUser"})

@Entity
@Table(name = "backlognotifications")
public class BacklogNotification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "backlognotifications_id_generator")
    @SequenceGenerator(name = "backlognotifications_id_generator", sequenceName = "backlognotifications_id_seq", allocationSize = 1)
    private Integer id;

    @Column(nullable = false)
    private Boolean notified = false;

    @ManyToOne
    @JoinColumn(nullable = false)
    @NonNull
    private Backlog backlog;

    @ManyToOne
    @JoinColumn(nullable = false, name = "notified_user")
    @NonNull
    private User notifiedUser;


}
