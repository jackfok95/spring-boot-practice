package com.jack.store.web.rest;

import com.jack.store.domain.User;
import com.jack.store.dto.UserDto;
import com.jack.store.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserResource extends AbstractResource<User, UserDto, Long>{

    private UserService userService;

    public UserResource(UserService userService){

        super(userService);
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerNewUser(@Valid @RequestBody UserDto dto){
        userService.register(dto);
    }

}
