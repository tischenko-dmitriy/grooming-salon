package ru.otus.example.grooming.model.results;

import ru.otus.example.grooming.model.Error;

public class SuccessWithError extends Success {

    private Error error;

    public SuccessWithError(boolean success, Error error) {
        super(success);
        this.error = error;
    }

    public Error getError() {
        return error;
    }

}
