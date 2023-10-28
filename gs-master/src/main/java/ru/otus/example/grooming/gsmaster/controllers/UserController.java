package ru.otus.example.grooming.gsmaster.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.example.grooming.gsmaster.model.dto.ScheduleDto;
import ru.otus.example.grooming.gsmaster.model.dto.UserDto;
import ru.otus.example.grooming.gsmaster.services.UserControllerService;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;
import java.util.Date;

@RestController
@RequestMapping(value = "/grooming/master")
public class UserController {

    private final UserControllerService userControllerService;

    public UserController(UserControllerService userControllerService) {
        this.userControllerService = userControllerService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto,
                                           HttpServletRequest httpServletRequest) throws URISyntaxException {
        String autorization = httpServletRequest.getHeader("Authorization");
        userControllerService.createMasterUser(userDto, autorization);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{masterId}/schedule")
    public ResponseEntity<ScheduleDto> getSchedule(@PathVariable(value = "masterId") Long masterId,
                                                   @RequestParam(name = "scheduleDate") Date scheduleDate) {

        ScheduleDto result = new ScheduleDto();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
