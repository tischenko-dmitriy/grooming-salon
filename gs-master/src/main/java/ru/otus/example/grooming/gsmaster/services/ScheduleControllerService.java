package ru.otus.example.grooming.gsmaster.services;

import org.springframework.stereotype.Service;
import ru.otus.example.grooming.gsmaster.model.dto.ScheduleDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ScheduleControllerService {

    private final MasterService masterService;

    public ScheduleControllerService(MasterService masterService) {
        this.masterService = masterService;
    }

    public ScheduleDto getSchedule(Long masterId, String scheduleDate) {
        LocalDate date = LocalDate.parse(scheduleDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return masterService.getSchedule(masterId, date);
    }

}
