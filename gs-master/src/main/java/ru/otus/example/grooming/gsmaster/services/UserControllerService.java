package ru.otus.example.grooming.gsmaster.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.otus.example.grooming.gsmaster.configuration.Constants;
import ru.otus.example.grooming.gsmaster.model.dto.MasterDto;
import ru.otus.example.grooming.gsmaster.model.dto.UserDto;
import ru.otus.example.grooming.gsmaster.model.results.SuccessWithId;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

@Service
public class UserControllerService {

    private final MasterService masterService;
    private final String adminAppUrl;
    private final String getUserRoleListUri;
    private final String createUserUri;
    private final String adminUsername;
    private final String adminPassword;
    private final RestTemplate restTemplate;

    @Autowired
    public UserControllerService(@Qualifier("adminAppProperties") Properties adminAppProperties,
                                 MasterService masterService) {
        this.adminAppUrl = adminAppProperties.getProperty("adminAppUrl");
        this.getUserRoleListUri = adminAppProperties.getProperty("getUserRoleListUri");
        this.createUserUri = adminAppProperties.getProperty("createUserUri");
        this.adminUsername = adminAppProperties.getProperty("adminUsername");
        this.adminPassword = adminAppProperties.getProperty("adminPassword");
        this.masterService = masterService;
        this.restTemplate = new RestTemplate();
    }

    public String getUserRoleList(String authorization) throws URISyntaxException {
        String uri = String.format("%s%s", adminAppUrl, getUserRoleListUri);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorization);
        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(new URI(uri), HttpMethod.GET, request, String.class);
        return response.getBody();
    }

    public void createMasterUser(UserDto userDto, String authorization) throws URISyntaxException {
        String uri = String.format("%s%s", adminAppUrl, createUserUri);
        userDto.setRole(Constants.MASTER_USER_ROLE_NAME);
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

        masterService.createMaster(userId, userDto.getLogin());
    }

}
