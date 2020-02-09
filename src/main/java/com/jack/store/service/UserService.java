package com.jack.store.service;

import com.jack.store.domain.Authority;
import com.jack.store.domain.User;
import com.jack.store.dto.UserDto;
import com.jack.store.exception.UserNameIsUsedException;
import com.jack.store.mapper.UserMapper;
import com.jack.store.repository.AuthorityRepository;
import com.jack.store.repository.UserRepository;
import com.jack.store.security.data.UserAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserService extends AbstractService<User, UserDto, Long>{

    private final UserRepository repository;

    private final UserMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityRepository authorityRepository;

    public UserService(UserRepository repository, UserMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    public void register(UserDto dto){

        repository.findUserByUsernameEquals(dto.getUsername()).ifPresent(user-> {throw new UserNameIsUsedException();});
        User user = mapper.toEntity(dto);
        user.setEnabled(true);

        Set<Authority> authority = new HashSet<>();
        authorityRepository.findById(UserAuthority.ROLE_USER).ifPresent(authority::add);
        user.setAuthorities(authority);

        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        repository.save(user);

    }
}
