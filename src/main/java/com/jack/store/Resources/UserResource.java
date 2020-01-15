package com.jack.store.Resources;

import com.jack.store.domain.User;
import com.jack.store.repository.ProductRepository;
import com.jack.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping()
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    @PostMapping()
    @Transactional
    public User create(@RequestBody User user){
        return userRepository.save(user);
    }

    @PostMapping("/date")
    @Transactional
    public User setDate(@RequestParam LocalDate date){
        return userRepository.findById(1L).get().setDateOfBirth(date);
    }



}
