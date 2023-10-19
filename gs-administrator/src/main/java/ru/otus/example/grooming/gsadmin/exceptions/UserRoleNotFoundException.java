package ru.otus.example.grooming.gsadmin.exceptions;

import lombok.Getter;

@Getter
public class UserRoleNotFoundException extends RuntimeException {

    private final String userRoleName;

    public UserRoleNotFoundException(String userRoleName) {
        super(userRoleName);
        this.userRoleName = userRoleName;
    }

}
