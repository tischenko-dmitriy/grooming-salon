package ru.otus.example.grooming.gsmaster.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.example.grooming.gsmaster.entities.MasterEntity;
import ru.otus.example.grooming.gsmaster.entities.ScheduleEntity;
import ru.otus.example.grooming.gsmaster.model.dto.ScheduleDto;
import ru.otus.example.grooming.gsmaster.repositories.MasterRepository;
import ru.otus.example.grooming.gsmaster.repositories.ScheduleItemRepository;
import ru.otus.example.grooming.gsmaster.repositories.ScheduleRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ScheduleServiceTest {

    @Mock
    ScheduleRepository scheduleRepository;

    @Mock
    ScheduleItemRepository scheduleItemRepository;

    @Mock
    MasterRepository masterRepository;

    @Spy
    @InjectMocks
    ScheduleService scheduleService;

    @BeforeEach
    public void init() {
        scheduleService = new ScheduleService(scheduleRepository, scheduleItemRepository, masterRepository);
    }

    @Test
    public void testCreateMaster() {
    }

    @Test
    public void testGetSchedule() {
        //given
        Long masterId = 102L;
        LocalDate date = LocalDate.of(2023, 11, 1);

        List<UUID> scheduleItemIds = new ArrayList<>();
        scheduleItemIds.add(UUID.randomUUID());
        scheduleItemIds.add(UUID.randomUUID());

        MasterEntity master = new MasterEntity();
        master.setId(masterId);
        master.setUserId(101L);
        master.setName("sidorov");
        master.setPhone("+79000000000");

        ScheduleEntity scheduleEntity = new ScheduleEntity();
        scheduleEntity.setId(104L);
        scheduleEntity.setDate(LocalDate.of(2023, 11, 1));
        scheduleEntity.setMasterId(102L);
        scheduleEntity.setStartTime(LocalTime.parse("09:00"));
        scheduleEntity.setEndTime(LocalTime.parse("18:00"));

        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setMasterId(102L);
        scheduleDto.setMasterName("sidorov");
        scheduleDto.setSchedule(scheduleEntity);
        scheduleDto.setScheduleItems(scheduleItemIds);

        when(scheduleRepository.findByMasterIdAndDate(masterId, date))
                .thenReturn(Optional.of(scheduleEntity));
        when(masterRepository.findById(masterId))
                .thenReturn(Optional.of(master));

        //when
        ScheduleDto actualScheduleDto = scheduleService.getSchedule(masterId, date);

        //then
        assertThat(actualScheduleDto.getMasterName()).isEqualTo(scheduleDto.getMasterName());

    }

    @Test
    public void testSetBusy() {
        //given
        Long scheduleId = 1000L;
        LocalTime time = LocalTime.of(9, 0);
        int scheduleItemCount = 3;


    }
}