package com.jack.store.service;

import com.jack.store.domain.User;
import com.jack.store.dto.UserDto;
import com.jack.store.mapper.UserMapper;
import com.jack.store.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService extends AbstractService<User, UserDto, Long>{

    public UserService(UserRepository repository, UserMapper mapper) {
        super(repository, mapper);
    }
}
