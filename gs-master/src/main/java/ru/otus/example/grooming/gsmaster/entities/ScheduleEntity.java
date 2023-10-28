package ru.otus.example.grooming.gsmaster.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_schedules")
@JsonAutoDetect
public class ScheduleEntity {

    @JsonProperty(value = "scheduleId")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_")
    private Long id;

    @JsonProperty(value = "scheduleDate")
    @Column(name = "date_")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate date;

    @JsonProperty(value = "masterId")
    @Column(name = "master_id_")
    private Long masterId;

    @JsonProperty(value = "startTime")
    @Column(name = "start_time_")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm")
    private LocalTime startTime;

    @JsonProperty(value = "endTime")
    @Column(name = "end_time_")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm")
    private LocalTime endTime;

}
