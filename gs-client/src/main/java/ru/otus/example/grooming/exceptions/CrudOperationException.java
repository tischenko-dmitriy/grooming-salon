package ru.otus.example.grooming.exceptions;

import lombok.Getter;

@Getter
public class CrudOperationException extends RuntimeException {

    private final String className;
    private final String message;


    public CrudOperationException(String className, String message) {
        super(message);
        this.className = className;
        this.message = className;
    }

}
