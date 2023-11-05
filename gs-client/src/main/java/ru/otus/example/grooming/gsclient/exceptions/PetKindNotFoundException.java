package ru.otus.example.grooming.gsclient.exceptions;

import lombok.Getter;

@Getter
public class PetKindNotFoundException extends RuntimeException {
    String name;

    public PetKindNotFoundException(String name) {
        super(String.format("This kind of pet is not found: [%s].", name));
        this.name = name;
    }

}
