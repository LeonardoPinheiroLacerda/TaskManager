package com.leonardo.taskmanager.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@ToString(exclude = {"leaderOf", "teams", "detailedHours", "backlogs", "notifications"})

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_generator")
    @SequenceGenerator(name = "users_id_generator", sequenceName = "users_id_seq", allocationSize = 1)
    private Integer id;

    @Column(nullable = false, length = 50)
    @NonNull
    private String name;

    @Column(nullable = false, length = 50)
    @NonNull
    private String lastName;

    @Column(nullable = false, length = 100, unique = true)
    @NonNull
    private String email;

    @Column(nullable = false, length = 50)
    @NonNull
    private String telephoneNumber;

    @Column(nullable = false, length = 50)
    @NonNull
    private String username;

    @Column(nullable = false, length = 255)
    @NonNull
    private String password;

    @OneToMany(mappedBy = "leader")
    private Set<Team> leaderOf;

    @ManyToMany(mappedBy = "users")
    private Set<Team> teams;

    @OneToMany(mappedBy = "user")
    private Set<DetailedHour> detailedHours;

    @OneToMany(mappedBy = "task")
    private Set<Backlog> backlogs;

    @OneToMany(mappedBy = "notifiedUser")
    private Set<BacklogNotification> notifications;

}
