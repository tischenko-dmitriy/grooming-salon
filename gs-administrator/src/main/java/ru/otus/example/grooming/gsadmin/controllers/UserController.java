package ru.otus.example.grooming.gsadmin.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.example.grooming.gsadmin.model.dto.SimpleDto;
import ru.otus.example.grooming.gsadmin.model.dto.UserDto;
import ru.otus.example.grooming.gsadmin.model.results.Success;
import ru.otus.example.grooming.gsadmin.services.UserControllerService;

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

    @GetMapping(value = "/role/list",
        produces = "application/json; charset = UTF-8")
    @ResponseBody
    public ResponseEntity<SimpleDto> getUserRoles() throws JsonProcessingException {
        SimpleDto result = userControllerService.getUserRoles();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/create",
            consumes = "application/json; charset=utf-8",
            produces = "application/json; charset=utf-8")
    public ResponseEntity<Success> createUser(@RequestBody UserDto userData) {
        userControllerService.createUser(userData);
        return new ResponseEntity<>(new Success(true), HttpStatus.OK);
    }
}
