package com.jack.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserNameIsUsedException extends BadRequestException {

    public UserNameIsUsedException(){

        super("User", "userName", "The username has been used");
    }
}
