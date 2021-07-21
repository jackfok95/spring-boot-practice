package com.jack.store.security.keycloak.customize;

import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.KeycloakDeploymentBuilder;
import org.keycloak.adapters.spi.HttpFacade;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.representations.adapters.config.AdapterConfig;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;

/***
 *  The properties can be set in application.yml directly
 *  If you want to do it out of the program directory, create this class.
 */
@Component
@PropertySource(value = "file:${project.keycloak-properties-path}")
public class KeycloakConfigResolver extends KeycloakSpringBootConfigResolver {

    private final KeycloakDeployment keycloakDeployment;

    public KeycloakConfigResolver(Environment env) {

        AdapterConfig adapterConfig = new AdapterConfig();
        adapterConfig.setAuthServerUrl(env.getProperty("auth-server-url"));
        adapterConfig.setRealm(env.getProperty("realm"));
        adapterConfig.setResource(env.getProperty("resource"));
        adapterConfig.setBearerOnly(Objects.equals(env.getProperty("bearer-only"), "true"));
        adapterConfig.setPrincipalAttribute("preferred_username");
        this.keycloakDeployment = KeycloakDeploymentBuilder.build(adapterConfig);

    }

    @Override
    public KeycloakDeployment resolve(HttpFacade.Request facade) {
        return this.keycloakDeployment;
    }
}