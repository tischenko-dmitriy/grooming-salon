package ru.otus.example.grooming.gsclient.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.example.grooming.gsclient.entities.PetEntity;

import java.util.Optional;

@Repository
public interface PetRepository extends CrudRepository<PetEntity, Long> {

    Optional<PetEntity> findByName(String name);

}
