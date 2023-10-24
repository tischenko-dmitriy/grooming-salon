package ru.otus.example.grooming.gsclient.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.example.grooming.gsclient.model.dto.ClientDto;
import ru.otus.example.grooming.gsclient.model.dto.UserDto;
import ru.otus.example.grooming.gsclient.services.UserControllerService;

import javax.servlet.http.HttpServletRequest;
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
    public ResponseEntity<String> getUserRoles() throws URISyntaxException {
        String result = userControllerService.getUserRoleList();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Void> createClientUser(@RequestBody UserDto userDto,
                                                 HttpServletRequest httpServletRequest) throws URISyntaxException {
        String autorization = httpServletRequest.getHeader("Authorization");
        userControllerService.createClientUser(userDto, autorization);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/update",
        consumes = "application/json; charset = UTF-8",
        produces = "application/json; charset = UTF-8")
    public ResponseEntity<Void> updateClient(@RequestBody ClientDto clientDto) {
        userControllerService.updateClient(clientDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
