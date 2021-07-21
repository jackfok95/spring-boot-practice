package com.jack.store.security.jwt.data;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginInfo{

    @NotNull
    private String username;

    @NotNull
    private String password;

    private Boolean rememberMe;

}
