package com.jack.store.exception;

public class UserNotActivatedException extends RuntimeException {

    private String username;

    public UserNotActivatedException(String username){
        this.username = username;
    }

    @Override
    public String getMessage() {

        return "User " + username + " is not activated";
    }
}
