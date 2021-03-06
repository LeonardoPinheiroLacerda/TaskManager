package com.leonardo.taskmanager.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@ToString(exclude = {"tasks", "team", "client"})

@Entity
@Table(name = "projects")
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teams_id_generator")
    @SequenceGenerator(name = "teams_id_generator", sequenceName = "teams_id_seq", allocationSize = 1)
    private Integer id;

    @Column(nullable = false, length = 50)
    @NonNull
    private String name;

    @ManyToOne
    @NonNull
    private Client client;

    @ManyToOne
    @NonNull
    private Team team;

    @OneToMany(mappedBy = "project")
    private Set<Task> tasks;

}
