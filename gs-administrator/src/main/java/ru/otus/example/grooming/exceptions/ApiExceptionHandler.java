package ru.otus.example.grooming.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.otus.example.grooming.model.results.SuccessWithError;
import ru.otus.example.grooming.model.Error;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(JsonProcessingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SuccessWithError handleJsonProcessingException(JsonProcessingException e) {
        int code = 40001;
        String type = "REQUEST_ERROR";
        String message = e.getMessage();
        return new SuccessWithError(false, new Error(code, type, message));
    }

    @ExceptionHandler(CrudOperationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public SuccessWithError handleCrudOperationException(CrudOperationException e) {
        int code = 50001;
        String type = "DATABASE_OPERATION_ERROR";
        String message = String.format("[%s] - %s", e.getClassName(), e.getMessage());
        return new SuccessWithError(false, new Error(code, type, message));
    }

}