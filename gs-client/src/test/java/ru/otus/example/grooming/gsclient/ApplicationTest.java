package ru.otus.example.grooming.gsclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import ru.otus.example.grooming.gsclient.configuration.BeanConfiguration;
import ru.otus.example.grooming.gsclient.configuration.security.ApiBasicAuthenticationEntryPoint;
import ru.otus.example.grooming.gsclient.configuration.security.ApiSecurityConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations="file:src/test/resources/gs-client.test.properties")
class ApplicationTest {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void contextLoads() {
    }

}