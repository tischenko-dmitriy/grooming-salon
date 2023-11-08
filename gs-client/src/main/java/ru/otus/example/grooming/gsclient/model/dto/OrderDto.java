package ru.otus.example.grooming.gsclient.model.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.otus.example.grooming.gsclient.entities.OrderEntity;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {

    @JsonProperty(value = "order")
    private OrderEntity order;

    @JsonProperty(value = "services")
    private List<ServiceDto> services;

    @JsonProperty(value = "startScheduleItemId")
    private UUID startScheduleItemId;
}
