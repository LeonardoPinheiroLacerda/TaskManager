package com.leonardo.taskmanager.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name = "detailedhours")
public class DetailedHour {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detailedhours_id_generator")
    @SequenceGenerator(name = "detailedhours_id_generator", sequenceName = "detailedhours_id_seq", allocationSize = 1)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String description;

    @Column(nullable = false)
    private LocalTime time;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    private Task task;

    @ManyToOne
    private User user;

}
