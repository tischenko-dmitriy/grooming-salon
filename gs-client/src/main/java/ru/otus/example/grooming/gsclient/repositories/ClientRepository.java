package ru.otus.example.grooming.gsclient.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.example.grooming.gsclient.entities.ClientEntity;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Long> {

    Optional<ClientEntity> findByName(String name);

}
