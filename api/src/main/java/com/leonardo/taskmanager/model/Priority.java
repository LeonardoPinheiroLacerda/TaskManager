package com.leonardo.taskmanager.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@ToString(exclude = {"tasks"})

@Entity
@Table(name = "priorities")
public class Priority {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "priorities_id_generator")
    @SequenceGenerator(name = "priorities_id_generator", sequenceName = "priorities_id_seq", allocationSize = 1)
    private Integer id;

    @Column(unique = true, nullable = false, length = 50)
    private String priotiry;

    @OneToMany(mappedBy = "priority")
    private Set<Task> tasks;

}
