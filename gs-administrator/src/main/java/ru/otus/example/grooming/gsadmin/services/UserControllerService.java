package ru.otus.example.grooming.gsadmin.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.otus.example.grooming.gsadmin.entities.UserEntity;
import ru.otus.example.grooming.gsadmin.entities.UserRoleEntity;
import ru.otus.example.grooming.gsadmin.exceptions.CrudOperationException;
import ru.otus.example.grooming.gsadmin.exceptions.UserRoleNotFoundException;
import ru.otus.example.grooming.gsadmin.model.dto.SimpleDto;
import ru.otus.example.grooming.gsadmin.model.dto.UserDto;
import ru.otus.example.grooming.gsadmin.repositories.UserRepository;
import ru.otus.example.grooming.gsadmin.repositories.UserRoleRepository;

import java.util.List;

@Service
public class UserControllerService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserControllerService(BCryptPasswordEncoder bCryptPasswordEncoder,
                                 UserRoleRepository userRoleRepository,
                                 UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
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

    public void createUser(UserDto userData) {
        try {
            userRepository.save(toUser(userData));
        } catch (Exception e) {
            throw new CrudOperationException(e.getClass().getName(), e.getMessage());
        }
    }

    private UserEntity toUser(UserDto userData) {

        UserRoleEntity userRoleEntity =
                userRoleRepository.findByName(userData.getRole())
                        .orElseThrow(() -> new UserRoleNotFoundException(userData.getRole()));

        UserEntity userEntity = new UserEntity();
        userEntity.setUserRoleId(userRoleEntity.getId());
        userEntity.setLogin(userData.getLogin());
        userEntity.setPassword(bCryptPasswordEncoder.encode(userData.getPassword()));
        userEntity.setEnabled(userData.getEnabled());

        return userEntity;
    }
}
