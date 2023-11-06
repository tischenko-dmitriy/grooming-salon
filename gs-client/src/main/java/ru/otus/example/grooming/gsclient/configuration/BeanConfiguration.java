package ru.otus.example.grooming.gsclient.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class BeanConfiguration {

    @Bean("adminAppProperties")
    public Properties configureAdminAppProperties(@Value("${grooming.admin-app.url}") String adminAppUrl,
                                                  @Value("${grooming.user.role-list.uri}") String getUserRoleListUri,
                                                  @Value("${grooming.user.create.uri}") String createUserUri) {
        Properties properties = new Properties();
        properties.put("adminAppUrl", adminAppUrl);
        properties.put("getUserRoleListUri", getUserRoleListUri);
        properties.put("createUserUri", createUserUri);
        return properties;
    }

    @Bean("masterAppProperties")
    public Properties configureMasterAppProperties(@Value("${grooming.master-app.url}") String masterAppUrl,
                                                   @Value("${grooming.schedule.list.uri}") String scheduleListUri) {
        Properties properties = new Properties();
        properties.put("masterAppUrl", masterAppUrl);
        properties.put("scheduleListUri", scheduleListUri);
        return properties;
    }
}
