package ru.otus.example.grooming.gsmaster.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.example.grooming.gsmaster.entities.ScheduleItemEntity;

import java.util.UUID;

@Repository
public interface ScheduleItemRepository extends CrudRepository<ScheduleItemEntity, UUID> {
}
