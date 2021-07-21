package com.jack.store.web.rest;

import com.jack.store.domain.User;
import com.jack.store.dto.ProductDto;
import com.jack.store.dto.UserDto;
import com.jack.store.mapper.ProductMapper;
import com.jack.store.repository.ProductRepository;
import com.jack.store.repository.UserRepository;
import com.jack.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

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

    @PutMapping("/{userId}/removeProducts")
    public void removeProducts(@PathVariable Long userId, @RequestBody List<ProductDto> products){
        userService.removeProducts(userId, products);
    }

    // Token should be get from here, or the validation will fail.
    @GetMapping("/getToken/{ac}")
    public String getToken(@PathVariable String ac){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("grant_type", "password");
        map.add("client_id", "fake-frontend");
        map.add("username", ac);
        map.add("password", ac);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity( "http://keycloak:8080/auth/realms/demo/protocol/openid-connect/token", request , String.class );
        return response.getBody();
    }

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    ProductMapper mapper;


}
