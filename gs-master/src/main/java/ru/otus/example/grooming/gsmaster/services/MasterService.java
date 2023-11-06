package ru.otus.example.grooming.gsmaster.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.example.grooming.gsmaster.entities.MasterEntity;
import ru.otus.example.grooming.gsmaster.entities.ScheduleEntity;
import ru.otus.example.grooming.gsmaster.entities.ScheduleItemEntity;
import ru.otus.example.grooming.gsmaster.exceptions.CannotCreateScheduleException;
import ru.otus.example.grooming.gsmaster.exceptions.MasterNotFoundException;
import ru.otus.example.grooming.gsmaster.model.dto.ScheduleDto;
import ru.otus.example.grooming.gsmaster.repositories.MasterRepository;
import ru.otus.example.grooming.gsmaster.repositories.ScheduleItemRepository;
import ru.otus.example.grooming.gsmaster.repositories.ScheduleRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

@Service
public class MasterService {

    private final MasterRepository masterRepository;
    private final ScheduleRepository scheduleRepository;
    private final ScheduleItemRepository scheduleItemRepository;

    @Autowired
    public MasterService(MasterRepository masterRepository,
                         ScheduleRepository scheduleRepository,
                         ScheduleItemRepository scheduleItemRepository) {
        this.masterRepository = masterRepository;
        this.scheduleRepository = scheduleRepository;
        this.scheduleItemRepository = scheduleItemRepository;
    }

    public void createMaster(Long userId, String name) {
        MasterEntity masterEntity = new MasterEntity(userId, name, "+70000000000");
        masterRepository.save(masterEntity);
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
            for (int i = 9; i < 19; i++) {
                ScheduleItemEntity scheduleItemEntity = new ScheduleItemEntity();
                scheduleItemEntity.setId(UUID.randomUUID());
                scheduleItemEntity.setScheduleId(scheduleEntity.getId());
                scheduleItemEntity.setStartTime(LocalTime.of(i, 0));
                scheduleItemRepository.save(scheduleItemEntity);
            }
        }

        return scheduleEntityToScheduleDto(scheduleEntity);
    }

    private ScheduleDto scheduleEntityToScheduleDto(ScheduleEntity scheduleEntity) {
        MasterEntity masterEntity = masterRepository.findById(scheduleEntity.getMasterId())
                .orElseThrow(MasterNotFoundException::new);

        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setMasterId(scheduleEntity.getMasterId());
        scheduleDto.setMasterName(masterEntity.getName());
        scheduleDto.setSchedule(scheduleEntity);

        return scheduleDto;
    }
}
