package ru.otus.example.grooming.gsclient.services;

import org.springframework.stereotype.Service;
import ru.otus.example.grooming.gsclient.model.dto.SimpleDto;

@Service
public class UserControllerService {


    public SimpleDto getUserRoleList() {

        return new SimpleDto();
    }

}
