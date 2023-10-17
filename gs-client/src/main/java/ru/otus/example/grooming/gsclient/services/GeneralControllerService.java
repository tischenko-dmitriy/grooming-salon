package ru.otus.example.grooming.gsclient.services;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

@Service
public class GeneralControllerService {

    public String testApplication() throws JsonProcessingException{
        return new ObjectMapper()
                .writerFor(AppInfo.class)
                .writeValueAsString(new AppInfo(System.getProperty("program.name")));
    }

    @JsonAutoDetect
    @Getter
    @Setter
    @AllArgsConstructor
    private static class AppInfo {
        private String appName;
    }

}
