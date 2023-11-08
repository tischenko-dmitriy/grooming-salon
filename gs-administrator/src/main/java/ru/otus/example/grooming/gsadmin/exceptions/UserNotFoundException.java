package ru.otus.example.grooming.gsadmin.exceptions;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {

    private final String login;

    public UserNotFoundException(String login) {
        super(login);
        this.login = login;
    }
}
