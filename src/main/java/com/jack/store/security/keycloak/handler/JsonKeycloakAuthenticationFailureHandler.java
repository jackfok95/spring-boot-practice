package com.jack.store.security.keycloak.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.keycloak.adapters.springsecurity.authentication.KeycloakCookieBasedRedirect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;


public class JsonKeycloakAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    ObjectMapper mapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // Check that the response was not committed yet (this may happen when another
        // part of the Keycloak adapter sends a challenge or a redirect).
        if (!response.isCommitted()) {
            if (KeycloakCookieBasedRedirect.getRedirectUrlFromCookie(request) != null) {
                response.addCookie(KeycloakCookieBasedRedirect.createCookieFromRedirectUrl(null));
            }

            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            LinkedHashMap<String, Object> outputMap = new LinkedHashMap<>();
            outputMap.put("status", HttpServletResponse.SC_UNAUTHORIZED);
            outputMap.put("error", exception.getMessage());
            outputMap.put("message", "Unable to authenticate using the Authorization header");
            outputMap.put("path", request.getPathInfo());

            response.getOutputStream().println(mapper.writeValueAsString(outputMap));

        } else {
            if (200 <= response.getStatus() && response.getStatus() < 300) {
                throw new RuntimeException("Success response was committed while authentication failed!", exception);
            }
        }
    }
}