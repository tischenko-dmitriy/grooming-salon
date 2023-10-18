package ru.otus.example.grooming.gsclient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.example.grooming.gsclient.entities.UserRoleEntity;
import ru.otus.example.grooming.gsclient.exceptions.CrudOperationException;
import ru.otus.example.grooming.gsclient.repositories.UserRoleRepository;

@Service
public class UserControllerService {

    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserControllerService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public void createUserRole(String name) {
        try {
            userRoleRepository.save(new UserRoleEntity(name));
        } catch (Exception e) {
            throw new CrudOperationException(e.getClass().getName(), e.getMessage());
        }
    }

}
