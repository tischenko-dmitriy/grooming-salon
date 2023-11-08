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
public class ClientDto {
    @JsonProperty(value = "clientId")
    private Long clientId;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "phone")
    private String phone;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "address")
    private String address;


}
