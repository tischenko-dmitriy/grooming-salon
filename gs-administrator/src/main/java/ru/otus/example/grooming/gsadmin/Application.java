package ru.otus.example.grooming.gsadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "ru.otus.example.grooming.gsadmin")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
