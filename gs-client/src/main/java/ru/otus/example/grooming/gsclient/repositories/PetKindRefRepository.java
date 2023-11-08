package ru.otus.example.grooming.gsclient.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.example.grooming.gsclient.entities.PetKindRefEntity;

import java.util.Optional;

@Repository
public interface PetKindRefRepository extends CrudRepository<PetKindRefEntity, Long> {

    Optional<PetKindRefEntity> findByName(String name);

}
