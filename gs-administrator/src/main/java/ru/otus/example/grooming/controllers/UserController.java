package ru.otus.example.grooming.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.example.grooming.model.dto.SimpleDto;
import ru.otus.example.grooming.model.results.Success;
import ru.otus.example.grooming.services.UserControllerService;

@RestController
@RequestMapping(value = "/grooming/admin/user")
public class UserController {

    private final UserControllerService userControllerService;

    @Autowired
    public UserController(UserControllerService userControllerService) {
        this.userControllerService = userControllerService;
    }

    @PostMapping(value = "/role/create",
        consumes = "application/json; charset=utf-8",
        produces = "application/json; charset=utf-8")
    public ResponseEntity<Success> createRole(@RequestBody SimpleDto userRoleName) {
        userControllerService.createUserRole(userRoleName.getData());
        return new ResponseEntity<>(new Success(true), HttpStatus.OK);
    }
}
