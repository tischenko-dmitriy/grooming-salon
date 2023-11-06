package ru.otus.example.grooming.gsmaster.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.example.grooming.gsmaster.entities.ScheduleItemEntity;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ScheduleItemRepository extends CrudRepository<ScheduleItemEntity, UUID> {

    List<ScheduleItemEntity> findAllByScheduleId(Long scheduleId);
    List<ScheduleItemEntity> findAllByIdInOrderByStartTime(List<UUID> ids);
    Optional<ScheduleItemEntity> findByScheduleIdAndStartTime(Long scheduleId, LocalTime startTime);

}
