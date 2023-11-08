package ru.otus.example.grooming.gsclient.exceptions;

import lombok.Getter;

@Getter
public class ServiceNotFoundException extends RuntimeException {

    private String name;

    public ServiceNotFoundException(String name) {
        super(name);
        this.name = name;
    }
}
