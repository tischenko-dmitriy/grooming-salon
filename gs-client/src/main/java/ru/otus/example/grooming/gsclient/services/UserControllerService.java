package ru.otus.example.grooming.gsclient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.otus.example.grooming.gsclient.model.dto.SimpleDto;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.Properties;

@Service
public class UserControllerService {

    private final String adminAppUrl;
    private final String userRoleListUri;
    private final String adminUsername;
    private final String adminPassword;

    @Autowired
    public UserControllerService(@Qualifier("adminAppProperties") Properties adminAppProperties) {
        this.adminAppUrl = adminAppProperties.getProperty("adminAppUrl");
        this.userRoleListUri = adminAppProperties.getProperty("userRoleListUri");
        this.adminUsername = adminAppProperties.getProperty("adminUsername");
        this.adminPassword = adminAppProperties.getProperty("adminPassword");
    }

    public String getUserRoleList() throws URISyntaxException {

        RestTemplate restTemplate = new RestTemplate();
        String uri = String.format("%s%s", adminAppUrl, userRoleListUri);
        String authorization = String.format("%s:%s", adminUsername, adminPassword);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + new String(Base64.getEncoder().encode(authorization.getBytes())));
        RequestEntity<Void> request = new RequestEntity<>(headers, HttpMethod.GET, new URI(uri));
        ResponseEntity<String> response = restTemplate. getForEntity(new URI(uri), String.class);
        return response.getBody();
    }

}
