package ru.otus.example.grooming.gsmaster.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.example.grooming.gsmaster.entities.UserRoleEntity;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRoleEntity, Long> {

}
