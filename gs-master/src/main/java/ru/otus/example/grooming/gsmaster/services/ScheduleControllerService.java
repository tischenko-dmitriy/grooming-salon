package ru.otus.example.grooming.gsmaster.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.example.grooming.gsmaster.model.dto.ScheduleDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class ScheduleControllerService {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleControllerService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    public ScheduleDto getSchedule(Long masterId, String scheduleDate) {
        LocalDate date = LocalDate.parse(scheduleDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return scheduleService.getSchedule(masterId, date);
    }

    public void setBusy(Long scheduleId, String startTime, Integer itemCount) {
        scheduleService.setBusy(scheduleId, startTime, itemCount);
    }
}
