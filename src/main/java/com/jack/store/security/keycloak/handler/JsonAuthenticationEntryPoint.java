package com.jack.store.security.keycloak.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jack.store.config.controllerAdvice.JsonResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;

public class JsonAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private ObjectMapper mapper;

    public JsonAuthenticationEntryPoint(ObjectMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {

        JsonResponseWrapper.fail(httpServletResponse, HttpStatus.UNAUTHORIZED, e);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        LinkedHashMap<String, Object> outputMap = new LinkedHashMap<>();
        outputMap.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        outputMap.put("error", e.getMessage());
        outputMap.put("message", "unauthenticated");
        outputMap.put("path", httpServletRequest.getPathInfo());

        httpServletResponse.getOutputStream().println(mapper.writeValueAsString(outputMap));
    }
}
