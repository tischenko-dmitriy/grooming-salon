package ru.otus.example.grooming.gsclient.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.example.grooming.gsclient.entities.OrderedServiceEntity;

@Repository
public interface OrderedServiceRepository extends CrudRepository<OrderedServiceEntity, Long> {
}
