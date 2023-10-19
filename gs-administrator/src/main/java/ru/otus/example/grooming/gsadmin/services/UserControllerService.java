package ru.otus.example.grooming.gsadmin.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.example.grooming.gsadmin.entities.UserRoleEntity;
import ru.otus.example.grooming.gsadmin.exceptions.CrudOperationException;
import ru.otus.example.grooming.gsadmin.model.dto.SimpleDto;
import ru.otus.example.grooming.gsadmin.repositories.UserRoleRepository;

import java.util.List;

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

    public SimpleDto getUserRoles() throws JsonProcessingException {
        List<UserRoleEntity> userRoles = (List<UserRoleEntity>) userRoleRepository.findAll();
        String data = new ObjectMapper()
                .writerFor(userRoles.getClass())
                .writeValueAsString(userRoles);
        return new SimpleDto(data);
    }

}
