package ru.otus.example.grooming.gsclient.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.example.grooming.gsclient.entities.UserRoleEntity;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRoleEntity, Long> {

}
