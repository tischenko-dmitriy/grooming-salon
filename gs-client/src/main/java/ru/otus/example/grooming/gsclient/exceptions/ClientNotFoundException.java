package ru.otus.example.grooming.gsclient.exceptions;

import lombok.Getter;

@Getter
public class ClientNotFoundException extends RuntimeException {
    private final String name;

    public ClientNotFoundException(String name) {
        super(String.format("Client [%s] not found.", name));
        this.name = name;
    }
}
