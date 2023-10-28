package ru.otus.example.grooming.gsmaster.model.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.otus.example.grooming.gsmaster.entities.ScheduleEntity;

@Getter
@Setter
@NoArgsConstructor
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduleDto {

    @JsonProperty(value = "masterId")
    private Long masterId;

    @JsonProperty(value = "masterName")
    private String masterName;

    @JsonProperty(value = "schedule")
    private ScheduleEntity schedule;

}
