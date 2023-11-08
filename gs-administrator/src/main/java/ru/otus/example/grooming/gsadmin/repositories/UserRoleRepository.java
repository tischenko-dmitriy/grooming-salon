package ru.otus.example.grooming.gsadmin.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.example.grooming.gsadmin.entities.UserRoleEntity;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRoleEntity, Long> {

    Optional<UserRoleEntity> findByName(String name);

}
