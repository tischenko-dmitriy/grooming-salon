package ru.otus.example.grooming.gsclient.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.example.grooming.gsclient.model.dto.SimpleDto;
import ru.otus.example.grooming.gsclient.services.UserControllerService;

import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/grooming/client/user")
public class UserController {

    private final UserControllerService userControllerService;

    public UserController(UserControllerService userControllerService) {
        this.userControllerService = userControllerService;
    }

    @GetMapping(value = "/role/list",
        produces = "application/json; charset = UTF-8")
    @ResponseBody
    public ResponseEntity<SimpleDto> getUserRoles() throws URISyntaxException {
        SimpleDto result = userControllerService.getUserRoleList();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
