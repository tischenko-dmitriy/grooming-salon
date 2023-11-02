package ru.otus.example.grooming.gsmaster.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.example.grooming.gsmaster.configuration.Constants;
import ru.otus.example.grooming.gsmaster.model.dto.ScheduleDto;
import ru.otus.example.grooming.gsmaster.model.dto.UserDto;
import ru.otus.example.grooming.gsmaster.services.UserControllerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URISyntaxException;
import java.util.Date;
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

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", httpServletRequest.getHeader("Authorization"));
        headers.add("Transact-Id", UUID.randomUUID().toString());
        headers.add("Source-App", Constants.APPLICATION_USER_TYPE);

        headers.forEach((k, v) -> httpServletResponse.addHeader(k, String.join(";", v)));

        userControllerService.createMasterUser(userDto, headers);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{masterId}/schedule")
    public ResponseEntity<ScheduleDto> getSchedule(@PathVariable(value = "masterId") Long masterId,
                                                   @RequestParam(name = "date") Date date) {

        ScheduleDto result = new ScheduleDto();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
