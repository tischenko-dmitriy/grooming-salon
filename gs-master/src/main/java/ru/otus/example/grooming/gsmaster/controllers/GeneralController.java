package ru.otus.example.grooming.gsmaster.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.example.grooming.gsmaster.configuration.Constants;
import ru.otus.example.grooming.gsmaster.services.GeneralControllerService;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping(value = "/grooming/master")
public class GeneralController {

    private final GeneralControllerService generalControllerService;

    @Autowired
    public GeneralController(GeneralControllerService generalControllerService) {
        this.generalControllerService = generalControllerService;

    }

    @GetMapping(value = "/test")
    public ResponseEntity<String> doTestApplication(HttpServletResponse httpServletResponse) throws JsonProcessingException {
        httpServletResponse.addHeader("Transact-Id", UUID.randomUUID().toString());
        httpServletResponse.addHeader("Source-App", Constants.APPLICATION_USER_TYPE);
        return new ResponseEntity<>(generalControllerService.testApplication(), HttpStatus.OK);
    }

}
