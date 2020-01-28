package com.jack.store.config.controllerAdvice;

import com.jack.store.exception.UserNameIsUsedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Throwable.class)
    public JsonResponseWrapper.ErrorResult exceptionHandler(HttpServletResponse response, Throwable e){

        return JsonResponseWrapper.fail(response, HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = EntityNotFoundException.class)
    public JsonResponseWrapper.ErrorResult entityNotFoundExceptionHandler(HttpServletResponse response, EntityNotFoundException e){

        String message = e.getMessage() != null ? e.getMessage() : "Entity Not Found";
        return JsonResponseWrapper.fail(response, HttpStatus.NOT_FOUND, message, e);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = UserNameIsUsedException.class)
    public JsonResponseWrapper.ErrorResult userNameIsUsedException(UserNameIsUsedException e){

        return JsonResponseWrapper.fail(HttpStatus.BAD_REQUEST, e);
    }
}
