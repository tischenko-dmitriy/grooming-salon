package ru.otus.example.grooming.gsclient.model.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceDto {

    @JsonProperty(value = "serviceId")
    private Long serviceId;

    @JsonProperty(value = "serviceName")
    private String serviceName;

    @JsonProperty(value = "petKindName")
    private String petKindName;

    @JsonProperty(value = "timingMinutes")
    private Integer timingMinutes;

    @JsonProperty(value = "price")
    private Double price;

}
