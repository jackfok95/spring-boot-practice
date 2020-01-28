package com.jack.store.config.controllerAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class JsonResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
//        return methodParameter.getDeclaringClass().isAnnotationPresent(RestController.class);
        return true;
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        HttpServletResponse servletResponse = ((ServletServerHttpResponse) serverHttpResponse).getServletResponse();
        HttpStatus httpStatus= HttpStatus.valueOf(servletResponse.getStatus());

        if(o instanceof JsonResponseWrapper.ErrorResult){
            return o;
        }

        else if (o instanceof String) {
            try {
                return objectMapper.writeValueAsString(JsonResponseWrapper.success(httpStatus, o));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return JsonResponseWrapper.success(httpStatus, o);
    }
}
