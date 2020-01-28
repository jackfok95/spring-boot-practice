package com.jack.store.web.rest;

import com.jack.store.domain.User;
import com.jack.store.dto.UserDto;
import com.jack.store.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserResource extends AbstractResource<User, UserDto, Long>{

    private UserService userService;

    public UserResource(UserService userService){

        super(userService);
        this.userService = userService;
    }

}
