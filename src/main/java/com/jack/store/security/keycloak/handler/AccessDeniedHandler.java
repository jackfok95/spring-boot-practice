package com.jack.store.security.keycloak.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;

public class AccessDeniedHandler extends AccessDeniedHandlerImpl {

    private ObjectMapper mapper;

    public AccessDeniedHandler(ObjectMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {

        if (!response.isCommitted()) {
            request.setAttribute("SPRING_SECURITY_403_EXCEPTION", accessDeniedException);

            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            LinkedHashMap<String, Object> outputMap = new LinkedHashMap<>();
            outputMap.put("status", HttpServletResponse.SC_FORBIDDEN);
            outputMap.put("error", accessDeniedException.getMessage());
            outputMap.put("path", request.getPathInfo());

            response.getOutputStream().println(mapper.writeValueAsString(outputMap));
        }
    }
}
