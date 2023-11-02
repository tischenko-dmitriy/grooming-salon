package ru.otus.example.grooming.gsadmin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.example.grooming.gsadmin.configuration.Constants;
import ru.otus.example.grooming.gsadmin.model.dto.SimpleDto;
import ru.otus.example.grooming.gsadmin.model.dto.UserDto;
import ru.otus.example.grooming.gsadmin.model.results.Success;
import ru.otus.example.grooming.gsadmin.model.results.SuccessWithId;
import ru.otus.example.grooming.gsadmin.services.UserControllerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

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
    public ResponseEntity<Success> createRole(@RequestBody SimpleDto userRoleName,
                                              HttpServletRequest httpServletRequest,
                                              HttpServletResponse httpServletResponse) {

        String transactId = httpServletRequest.getHeader("Transact-Id");
        transactId = Objects.isNull(transactId) ? "-" : transactId;
        httpServletResponse.addHeader("Transact-Id", transactId);

        userControllerService.createUserRole(userRoleName.getData());
        return new ResponseEntity<>(new Success(true), HttpStatus.OK);
    }

    @GetMapping(value = "/role/list",
        produces = "application/json; charset = UTF-8")
    @ResponseBody
    public ResponseEntity<String> getUserRoles(HttpServletResponse httpServletResponse) throws IOException {

        httpServletResponse.addHeader("Transact-Id", UUID.randomUUID().toString());
        httpServletResponse.addHeader("Source-App", Constants.APPLICATION_USER_TYPE);

        return new ResponseEntity<>(userControllerService.getUserRoles(), HttpStatus.OK);
    }

    @PostMapping(value = "/create",
            consumes = "application/json; charset=utf-8",
            produces = "application/json; charset=utf-8")
    public ResponseEntity<SuccessWithId> createUser(@RequestBody UserDto userData,
                                                    HttpServletRequest httpServletRequest,
                                                    HttpServletResponse httpServletResponse) {

        String transactId = httpServletRequest.getHeader("Transact-Id");
        String source = httpServletRequest.getHeader("Source-App");

        source = Objects.nonNull(source) ? source : Constants.APPLICATION_USER_TYPE;
        transactId = Objects.nonNull(transactId) ? transactId : UUID.randomUUID().toString();
        httpServletResponse.addHeader("Transact-Id", transactId);
        httpServletResponse.addHeader("Source-App", source);

        Long id = userControllerService.createUser(userData);
        return new ResponseEntity<>(new SuccessWithId(true, id), HttpStatus.OK);
    }

}
