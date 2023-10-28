package ru.otus.example.grooming.gsmaster.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.example.grooming.gsmaster.entities.ScheduleEntity;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends CrudRepository<ScheduleEntity, Long> {

    Optional<ScheduleEntity> findByMasterIdAndDate(Long masterId, LocalDate date);

}
