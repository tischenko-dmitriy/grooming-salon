package ru.otus.example.grooming.gsmaster.model.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    @JsonProperty(value = "role")
    private String role;

    @JsonProperty(value = "login")
    private String login;

    @JsonProperty(value = "password")
    private String password;

    @JsonProperty(value = "enabled")
    private Boolean enabled;

}
