package ru.otus.example.grooming.gsclient.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
public class UserControllerServiceTest {

    private final RestTemplate restTemplate = new RestTemplate();
    private final MockRestServiceServer mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);

    String newRole = "{\n" +
            "    \"role\": \"Клиент\",\n" +
            "    \"login\": \"petrov\",\n" +
            "    \"password\": \"petrov\",\n" +
            "    \"enabled\": true\n" +
            "}";
    String createUserUrl = "http://gs-admin:8082/grooming/admin/user/create";

    @BeforeEach
    public void setUp() {
        this.mockRestServiceServer.reset();
    }

    @Test
    public void getUserRoleList() {
    }

    @Test
    public void createClientUser() {
/*
        mockRestServiceServer
                .expect(ExpectedCount.times(1), MockRestRequestMatchers.requestTo(createUserUrl))
                .andExpect(MockRestRequestMatchers.content().json(newRole))
                .andRespond(MockRestResponseCreators.withSuccess("{\"resultCode\":\"0\",\"resultComment\":\"\",\"orderStatus\":\"ORDER_STATUS_PROCESSING\",\"orderStatusDateTime\":\"2022-01-13T20:12:58.190+05:00\",\"connectionList\":{\"item\":{\"connectionId\":\"16499360\",\"phoneNum\":\"79398225261\"}}}", MediaType.APPLICATION_JSON));
*/
/*
        mpzService.register(date);
*/
    }

    @Test
    public void updateClient() {
    }

}