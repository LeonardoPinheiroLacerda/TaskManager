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
@Table(name = "detailedhours")
public class DetailedHour {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detailedhours_id_generator")
    @SequenceGenerator(name = "detailedhours_id_generator", sequenceName = "detailedhours_id_seq", allocationSize = 1)
    private Integer id;

    @Column(nullable = false, length = 100)
    @NonNull
    private String description;

    @Column(nullable = false)
    @NonNull
    private LocalTime time;

    @Column(nullable = false)
    @NonNull
    private LocalDateTime createdAt;

    @ManyToOne
    @NonNull
    private Task task;

    @ManyToOne
    @NonNull
    private User user;

}
