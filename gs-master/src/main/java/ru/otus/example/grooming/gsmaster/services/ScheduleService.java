package ru.otus.example.grooming.gsmaster.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.example.grooming.gsmaster.entities.MasterEntity;
import ru.otus.example.grooming.gsmaster.entities.ScheduleEntity;
import ru.otus.example.grooming.gsmaster.entities.ScheduleItemEntity;
import ru.otus.example.grooming.gsmaster.exceptions.CannotCreateScheduleException;
import ru.otus.example.grooming.gsmaster.exceptions.MasterNotFoundException;
import ru.otus.example.grooming.gsmaster.exceptions.ScheduleItemNotFoundException;
import ru.otus.example.grooming.gsmaster.model.dto.ScheduleDto;
import ru.otus.example.grooming.gsmaster.repositories.MasterRepository;
import ru.otus.example.grooming.gsmaster.repositories.ScheduleItemRepository;
import ru.otus.example.grooming.gsmaster.repositories.ScheduleRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleItemRepository scheduleItemRepository;
    private final MasterRepository masterRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository,
                           ScheduleItemRepository scheduleItemRepository,
                           MasterRepository masterRepository) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleItemRepository = scheduleItemRepository;
        this.masterRepository = masterRepository;
    }

    public ScheduleDto getSchedule(Long masterId, LocalDate date) {
        ScheduleEntity scheduleEntity = scheduleRepository.findByMasterIdAndDate(masterId, date).orElse(null);
        if (Objects.isNull(scheduleEntity)) {
            scheduleEntity = new ScheduleEntity();
            scheduleEntity.setMasterId(masterId);
            scheduleEntity.setDate(date);
            scheduleEntity.setStartTime(LocalTime.parse("09:00"));
            scheduleEntity.setEndTime(LocalTime.parse("18:00"));
            scheduleRepository.save(scheduleEntity);
            scheduleEntity = scheduleRepository.findById(scheduleEntity.getId())
                    .orElseThrow(CannotCreateScheduleException::new);
            for (int i = 9; i < 18; i++) {
                ScheduleItemEntity scheduleItemEntity = new ScheduleItemEntity();
                scheduleItemEntity.setId(UUID.randomUUID());
                scheduleItemEntity.setScheduleId(scheduleEntity.getId());
                scheduleItemEntity.setStartTime(LocalTime.of(i, 0));
                scheduleItemRepository.save(scheduleItemEntity);
            }
        }

        return scheduleEntityToScheduleDto(scheduleEntity);
    }

    public void setBusy(Long scheduleId, String startTime, Integer itemCount) {
        LocalTime time = LocalTime.parse(startTime);
        int hour = time.getHour() - 1;
        for (int i = 0; i < itemCount; i++) {
            hour++;
            ScheduleItemEntity scheduleItemEntity =
                    scheduleItemRepository.findByScheduleIdAndStartTime(scheduleId, LocalTime.of(hour, 0))
                            .orElseThrow(ScheduleItemNotFoundException::new);
            scheduleItemEntity.setBusy(true);
            scheduleItemRepository.save(scheduleItemEntity);
        }

    }

    private ScheduleDto scheduleEntityToScheduleDto(ScheduleEntity scheduleEntity) {
        MasterEntity masterEntity = masterRepository.findById(scheduleEntity.getMasterId())
                .orElseThrow(MasterNotFoundException::new);

        List<UUID> scheduleItems = new ArrayList<>();
        scheduleItemRepository.findAllByScheduleId(scheduleEntity.getId())
                .forEach(i -> scheduleItems.add(i.getId()));

        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setMasterId(scheduleEntity.getMasterId());
        scheduleDto.setMasterName(masterEntity.getName());
        scheduleDto.setSchedule(scheduleEntity);
        scheduleDto.setScheduleItems(scheduleItems);

        return scheduleDto;
    }

}
