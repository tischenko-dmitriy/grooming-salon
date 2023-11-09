package ru.otus.example.grooming.gsclient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.otus.example.grooming.gsclient.configuration.Constants;
import ru.otus.example.grooming.gsclient.model.dto.ClientDto;
import ru.otus.example.grooming.gsclient.model.dto.PetDto;
import ru.otus.example.grooming.gsclient.model.dto.ServiceDto;
import ru.otus.example.grooming.gsclient.model.dto.UserDto;
import ru.otus.example.grooming.gsclient.model.results.SuccessWithId;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Properties;

@Service
public class UserControllerService {

    private final ClientService clientService;
    private final String adminAppUrl;
    private final String adminUsername;
    private final String adminPassword;
    private final String createUserUri;
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

    public void createClientUser(UserDto userDto, HttpHeaders httpHeaders) throws URISyntaxException {
        String uri = String.format("%s%s", adminAppUrl, createUserUri);
        userDto.setRole(Constants.CLIENT_USER_ROLE_NAME);
        RequestEntity<UserDto> request = new RequestEntity<>(
                userDto,
                httpHeaders,
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

    public void createPet(PetDto petDto) {
        clientService.createPet(petDto);
    }

    public List<ServiceDto> getServiceList(String petKindName) {
        List<ServiceDto> serviceDtos = clientService.getServiceList(petKindName);
        return serviceDtos;
    }
}
