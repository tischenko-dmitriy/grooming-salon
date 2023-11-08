package ru.otus.example.grooming.gsmaster.model.dto;

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
public class MasterDto {
    @JsonProperty(value = "masterId")
    private Long masterId;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "phone")
    private String phone;

    @JsonProperty(value = "email")
    private String email;


}
