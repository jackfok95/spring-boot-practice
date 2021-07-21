package com.jack.store.security.keycloak.handler;


import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticationProcessingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakFailureHandlerConfig {

    @Autowired
    KeycloakAuthenticationProcessingFilter filter;

    @Bean
    public JsonKeycloakAuthenticationFailureHandler failureHandler(){
        return new JsonKeycloakAuthenticationFailureHandler();
    }

    @Bean
    public KeycloakAuthenticationProcessingFilter customHandler(){
        filter.setAuthenticationFailureHandler(failureHandler());
        return filter;
    }
}
