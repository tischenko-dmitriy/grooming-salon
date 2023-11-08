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
public class PetDto {

    @JsonProperty(value = "petName")
    private String name;

    @JsonProperty(value = "petKind")
    private String kind;

    @JsonProperty(value = "clientName")
    private String client;

}
