package com.jack.store.config.controllerAdvice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

public class JsonResponseWrapper implements Serializable {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    public class SuccessResult {

        private Integer code;

        private String message;

        private Object data;

    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Accessors(chain = true)
    public class ErrorResult {

        private Integer code;

        private String message;

        private String exception;

    }

    static SuccessResult success(HttpStatus httpStatus, String message, Object data){
        return success(httpStatus, data).setMessage(message);
    }

    static SuccessResult success(HttpStatus httpStatus, Object data){

        JsonResponseWrapper wrapper = new JsonResponseWrapper();
        JsonResponseWrapper.SuccessResult successResult = wrapper.new SuccessResult();

        return  successResult
                .setCode(httpStatus.value())
                .setMessage(httpStatus.getReasonPhrase())
                .setData(data);
    }

    static ErrorResult fail(HttpServletResponse response, HttpStatus httpStatus, Throwable e){

        response.setStatus(httpStatus.value());

        return fail(httpStatus, e);
    }

    static ErrorResult fail(HttpServletResponse response, HttpStatus httpStatus, String message, Throwable e){

        return fail(response, httpStatus, e).setMessage(message);
    }

    static ErrorResult fail(HttpStatus httpStatus, Throwable e){

        JsonResponseWrapper wrapper = new JsonResponseWrapper();
        JsonResponseWrapper.ErrorResult errorResult = wrapper.new ErrorResult();
        e.printStackTrace();
        return errorResult
                .setCode(httpStatus.value())
                .setMessage(e.getMessage())
                .setException(e.getClass().getName());
    }
}
