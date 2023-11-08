package ru.otus.example.grooming.gsadmin.model.results;

public class Success {
    private boolean success;

    public Success() {
        this.success = true;
    }

    public Success(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

}
