package com.jack.store.security.jwt;

import com.jack.store.security.jwt.data.LoginInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

//@Slf4j
@RestController
@RequestMapping("/authenticate")
public class JwtController {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    public JwtController(AuthenticationManager authenticationManager, JwtUtil jwtUtil){
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public JwtToken authenticate(@Valid @RequestBody LoginInfo loginInfo){

//        log.info("Login with username: {}", loginInfo.getUsername());

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginInfo.getUsername(), loginInfo.getPassword()));

        // in DomainUserDetailsService, I return a security User object
//        User user = (User)(authentication.getPrincipal());

        boolean rememberMe = loginInfo.getRememberMe() == null ? false : loginInfo.getRememberMe();
        return new JwtToken(jwtUtil.createToken(authentication, rememberMe));
    }

    @Data
    @AllArgsConstructor
    private static class JwtToken{

        private String token;

    }

}
