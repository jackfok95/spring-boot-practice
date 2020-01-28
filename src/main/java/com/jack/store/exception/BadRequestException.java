package com.jack.store.exception;

public class BadRequestException extends RuntimeException {


    private String entityName;

    private String paramName;

    private String message;

    public BadRequestException(String entityName, String paramName, String message){

        this.entityName = entityName;
        this.paramName = paramName;
        this.message = message;
    }

    @Override
    public String getMessage() {

        return "Parameter '" + paramName + "' of Entity '"+ entityName + "': "+ message;
    }
}
