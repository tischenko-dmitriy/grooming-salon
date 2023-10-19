package ru.otus.example.grooming.gsclient.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonAutoDetect
@Getter
@Setter
public class Error {

    @JsonProperty("code")
    private final Integer code;
    @JsonProperty("type")
    private final String type;
    @JsonProperty("message")
    private final String message;

    public Error(Integer code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

}
