package ru.otus.example.grooming.gsclient.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class BeanConfiguration {

    @Bean("adminAppProperties")
    public Properties configureAdminAppProperties(@Value("${grooming.admin-app.url}") String adminAppUrl,
                                                  @Value("${grooming.user-role-list.uri}") String userRoleListUri) {
        Properties properties = new Properties();
        properties.put("adminAppUrl", adminAppUrl);
        properties.put("userRoleListUri", userRoleListUri);
        return properties;
    }

}
