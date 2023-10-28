package ru.otus.example.grooming.gsclient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.otus.example.grooming.gsclient.model.dto.ClientDto;
import ru.otus.example.grooming.gsclient.model.dto.UserDto;
import ru.otus.example.grooming.gsclient.model.results.SuccessWithId;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

@Service
public class UserControllerService {

    private final ClientService clientService;
    private final String adminAppUrl;
    private final String createUserUri;
    private final String adminUsername;
    private final String adminPassword;
    private final RestTemplate restTemplate;

    @Autowired
    public UserControllerService(@Qualifier("adminAppProperties") Properties adminAppProperties,
                                 ClientService clientService) {
        this.adminAppUrl = adminAppProperties.getProperty("adminAppUrl");
        this.createUserUri = adminAppProperties.getProperty("createUserUri");
        this.adminUsername = adminAppProperties.getProperty("adminUsername");
        this.adminPassword = adminAppProperties.getProperty("adminPassword");
        this.clientService = clientService;
        this.restTemplate = new RestTemplate();
    }

    public void createClientUser(UserDto userDto, String authorization) throws URISyntaxException {
        String uri = String.format("%s%s", adminAppUrl, createUserUri);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorization);
        RequestEntity<UserDto> request = new RequestEntity<>(
                userDto,
                headers,
                HttpMethod.POST,
                new URI(uri)
        );
        ResponseEntity<SuccessWithId> response = restTemplate.exchange(request, SuccessWithId.class);
        Long userId = response.getBody().getId();

        clientService.createClient(userId, userDto.getLogin());
    }

    public void updateClient(ClientDto clientDto) {
        clientService.updateClient(clientDto);
    }

}
