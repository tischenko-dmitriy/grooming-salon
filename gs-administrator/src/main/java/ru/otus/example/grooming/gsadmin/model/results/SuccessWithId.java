package ru.otus.example.grooming.gsadmin.model.results;

public class SuccessWithId {

    private Boolean success;
    private Long id;

    public SuccessWithId() {

    }

    public SuccessWithId(boolean success, Long id) {
        this.success = success;
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public Long getId() {
        return id;
    }

}
