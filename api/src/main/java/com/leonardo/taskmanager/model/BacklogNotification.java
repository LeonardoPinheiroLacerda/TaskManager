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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
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
    private Boolean notified;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Backlog backlog;

    @ManyToOne
    @JoinColumn(nullable = false, name = "notified_user")
    private User notifiedUser;


}
