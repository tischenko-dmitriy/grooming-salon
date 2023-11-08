package ru.otus.example.grooming.gsclient.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.example.grooming.gsclient.entities.PetKindRefEntity;
import ru.otus.example.grooming.gsclient.entities.ServiceEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRepository extends CrudRepository<ServiceEntity, Long> {

    List<ServiceEntity> findAllByPetKindId(Long petKindId);

}
