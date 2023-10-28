package ru.otus.example.grooming.gsmaster.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.example.grooming.gsmaster.model.dto.MasterDto;
import ru.otus.example.grooming.gsmaster.model.dto.UserDto;
import ru.otus.example.grooming.gsmaster.services.UserControllerService;

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
    public ResponseEntity<String> getUserRoles(HttpServletRequest httpServletRequest) throws URISyntaxException {
        String result = userControllerService.getUserRoleList(httpServletRequest.getHeader("Authorization"));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Void> createClientUser(@RequestBody UserDto userDto,
                                                 HttpServletRequest httpServletRequest) throws URISyntaxException {
        String autorization = httpServletRequest.getHeader("Authorization");
        userControllerService.createMasterUser(userDto, autorization);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
