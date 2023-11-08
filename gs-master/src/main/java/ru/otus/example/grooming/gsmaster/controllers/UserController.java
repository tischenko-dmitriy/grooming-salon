package ru.otus.example.grooming.gsmaster.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.example.grooming.gsmaster.configuration.Constants;
import ru.otus.example.grooming.gsmaster.model.dto.UserDto;
import ru.otus.example.grooming.gsmaster.services.UserControllerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URISyntaxException;
import java.util.UUID;

@RestController
@RequestMapping(value = "/grooming/master")
public class UserController {

    private final UserControllerService userControllerService;

    public UserController(UserControllerService userControllerService) {
        this.userControllerService = userControllerService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto,
                                           HttpServletRequest httpServletRequest,
                                           HttpServletResponse httpServletResponse) throws URISyntaxException {

        HttpHeaders headers = addHeaders(httpServletResponse, "CreateMaster");
        headers.add("Authorization", httpServletRequest.getHeader("Authorization"));
        userControllerService.createMasterUser(userDto, headers);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private HttpHeaders addHeaders(HttpServletResponse response, String actionName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Transact-Id", UUID.randomUUID().toString());
        headers.add("Source-App", Constants.APPLICATION_USER_TYPE);
        headers.add("Action", actionName);
        headers.forEach((k, v) -> response.addHeader(k, String.join(";", v)));
        return headers;
    }

}
