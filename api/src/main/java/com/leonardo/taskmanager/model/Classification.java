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
@Table(name = "Classifications")
public class Classification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "classifications_id_generator")
    @SequenceGenerator(name = "classifications_id_generator", sequenceName = "classifications_id_seq", allocationSize = 1)
    private Integer id;

    @Column(unique = true, nullable = false, length = 50)
    private String classification;

    @OneToMany(mappedBy = "classification")
    private Set<Task> tasks;

}
