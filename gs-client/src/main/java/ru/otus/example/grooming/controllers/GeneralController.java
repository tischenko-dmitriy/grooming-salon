package ru.otus.example.grooming.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.example.grooming.services.GeneralControllerService;

@RestController
@RequestMapping(value = "/grooming/client")
public class GeneralController {

    private final GeneralControllerService generalControllerService;

    @Autowired
    public GeneralController(GeneralControllerService generalControllerService) {
        this.generalControllerService = generalControllerService;

    }

    @GetMapping(value = "/test")
    public ResponseEntity<String> doTestApplication() throws JsonProcessingException {
        return new ResponseEntity<>(generalControllerService.testApplication(), HttpStatus.OK);
    }

}
