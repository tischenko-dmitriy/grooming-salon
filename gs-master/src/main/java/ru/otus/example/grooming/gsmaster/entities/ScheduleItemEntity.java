package ru.otus.example.grooming.gsmaster.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_schedule_items")
public class ScheduleItemEntity {

    @Id
    @Column(name = "id_")
    private UUID id;

    @Column(name = "schedule_id_")
    private Long scheduleId;

    @Column(name = "client_id_")
    private Long clientId;

    @Column(name = "start_time_")
    private LocalTime startTime;

    @Column(name = "busy_")
    private Boolean busy;

}
