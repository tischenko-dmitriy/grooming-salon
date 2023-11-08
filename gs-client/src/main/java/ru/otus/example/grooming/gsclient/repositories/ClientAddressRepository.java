package ru.otus.example.grooming.gsclient.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.example.grooming.gsclient.entities.ClientAddressEntity;

@Repository
public interface ClientAddressRepository extends CrudRepository<ClientAddressEntity, Long> {
}
