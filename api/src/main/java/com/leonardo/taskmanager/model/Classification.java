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

import com.leonardo.taskmanager.dtos.ClassificationDTO;

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
@ToString(exclude = {"tasks"})

@Entity
@Table(name = "Classifications")
public class Classification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "classifications_id_generator")
    @SequenceGenerator(name = "classifications_id_generator", sequenceName = "classifications_id_seq", allocationSize = 1)
    private Integer id;

    @Column(unique = true, nullable = false, length = 50)
    @NonNull
    private String classification;

    @OneToMany(mappedBy = "classification")
    private Set<Task> tasks;

    public ClassificationDTO toDTO(){
        return new ClassificationDTO(this.id, this.classification);
    }

}
