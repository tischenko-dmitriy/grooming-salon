package ru.otus.example.grooming.gsclient.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_")
    private Long id;

    @Column(name = "date_")
    private LocalDate date;

    @Column(name = "schedule_item_id_")
    private Long scheduleItemId;

    @Column(name = "pet_id_")
    private Long petId;

    @Column(name = "total_timing_minutes_")
    private int totalTimingMinutes;

}
