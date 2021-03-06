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

import com.leonardo.taskmanager.dtos.ClientDTO;

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
@ToString(exclude = {"projects"})

@Entity
@Table(name = "clientes")
public class Client {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clients_id_generator")
    @SequenceGenerator(name = "clients_id_generator", sequenceName = "clients_id_seq", allocationSize = 1)
    private Integer id;

    @Column(unique = true, nullable = false, length = 50)
    @NonNull
    private String client;

    @OneToMany(mappedBy = "client")
    private Set<Project> projects;


    public ClientDTO toDTO(){
        return new ClientDTO(this.id, this.client);
    }

}
