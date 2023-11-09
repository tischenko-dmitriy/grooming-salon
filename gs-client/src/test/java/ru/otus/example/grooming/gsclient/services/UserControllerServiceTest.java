package ru.otus.example.grooming.gsclient.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import ru.otus.example.grooming.gsclient.model.dto.UserDto;

import java.net.URI;

@ExtendWith(MockitoExtension.class)
public class UserControllerServiceTest {

    @Mock
    RestTemplate restTemplate;

    @Mock
    ClientService clientService;

    @InjectMocks
    UserControllerService userControllerService;

    private String newRole = "{\n" +
            "    \"role\": \"Клиент\",\n" +
            "    \"login\": \"petrov\",\n" +
            "    \"password\": \"petrov\",\n" +
            "    \"enabled\": true\n" +
            "}";
    private String createUserUrl = "http://localhost:8082/grooming/admin/user/create";

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void getUserRoleList() {
    }

    @Test
    public void createClientUser() {
        //given
/*
        RequestEntity<UserDto> request = new RequestEntity<>(
                userDto,
                httpHeaders,
                HttpMethod.POST,
                new URI(uri)
        );
*/

    }

    @Test
    public void updateClient() {
    }

}