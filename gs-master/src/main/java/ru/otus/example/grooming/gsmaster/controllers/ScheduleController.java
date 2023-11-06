package ru.otus.example.grooming.gsmaster.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.example.grooming.gsmaster.configuration.Constants;
import ru.otus.example.grooming.gsmaster.model.dto.ScheduleDto;
import ru.otus.example.grooming.gsmaster.services.ScheduleControllerService;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping(value = "/grooming/master")
public class ScheduleController {

    private final ScheduleControllerService scheduleControllerService;

    @Autowired
    public ScheduleController(ScheduleControllerService scheduleControllerService) {
        this.scheduleControllerService = scheduleControllerService;
    }

    @GetMapping(value = "/{masterId}/schedule")
    public ResponseEntity<ScheduleDto> getSchedule(@PathVariable(value = "masterId") Long masterId,
                                                   @RequestParam(name = "date") String date,
                                                   HttpServletResponse httpServletResponse) {

        addHeaders(httpServletResponse, "GetSchedule");
        ScheduleDto result = scheduleControllerService.getSchedule(masterId, date);
        return new ResponseEntity<>(result, HttpStatus.OK);
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
