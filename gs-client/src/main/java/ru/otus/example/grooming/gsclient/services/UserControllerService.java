package ru.otus.example.grooming.gsclient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.otus.example.grooming.gsclient.model.dto.SimpleDto;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

@Service
public class UserControllerService {

    private final String adminAppUrl;
    private final String userRoleListUri;

    @Autowired
    public UserControllerService(@Qualifier("adminAppProperties") Properties adminAppProperties) {
        this.adminAppUrl = adminAppProperties.getProperty("adminAppUrl");
        this.userRoleListUri = adminAppProperties.getProperty("userRoleListUri");
    }

    public SimpleDto getUserRoleList() throws URISyntaxException {

        RestTemplate restTemplate = new RestTemplate();
        String uri = String.format("%s%s", adminAppUrl, userRoleListUri);

        ResponseEntity<SimpleDto> response = restTemplate.getForEntity(new URI(uri), SimpleDto.class);
        return new SimpleDto();
    }

}
