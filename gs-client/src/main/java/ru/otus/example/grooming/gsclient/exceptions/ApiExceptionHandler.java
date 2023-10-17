package ru.otus.example.grooming.gsclient.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.otus.example.grooming.gsclient.model.results.SuccessWithError;
import ru.otus.example.grooming.gsclient.model.Error;

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

}