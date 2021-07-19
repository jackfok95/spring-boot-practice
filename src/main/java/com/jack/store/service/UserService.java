package com.jack.store.service;

import com.jack.store.domain.Authority;
import com.jack.store.domain.Product;
import com.jack.store.domain.User;
import com.jack.store.dto.ProductDto;
import com.jack.store.dto.UserDto;
import com.jack.store.exception.UserNameIsUsedException;
import com.jack.store.mapper.ProductMapper;
import com.jack.store.mapper.UserMapper;
import com.jack.store.repository.AuthorityRepository;
import com.jack.store.repository.ProductRepository;
import com.jack.store.repository.UserRepository;
import com.jack.store.security.data.UserAuthority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class UserService extends AbstractService<User, UserDto, Long>{

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final ProductRepository productRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthorityRepository authorityRepository;

    public UserService(UserRepository userRepository, UserMapper userMapper, ProductRepository productRepository, PasswordEncoder passwordEncoder,
                       AuthorityRepository authorityRepository){

        super(userRepository, userMapper);
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.productRepository = productRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    // Delete a User will delete all products. This method reduce the SQL.
    @Override
    public void delete(Long id){
        // delete all child first in one single SQL to avoid hibernate delete one by one
        productRepository.deleteAllByUserId(id);
        super.delete(id);
    }

    public void removeProducts(Long userId, List<ProductDto> productDtos){
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        List<Long> ids = productDtos.stream().map(ProductDto::getId).collect(Collectors.toList());
        List<Product> products = user.getProducts().stream().filter(p->ids.contains(p.getId())).collect(Collectors.toList());
//        orphanRemoval = true so that removed product will be deleted from the database
        products.forEach(user::removeProduct);
    }

    public void register(UserDto dto){

        userRepository.findUserByUsernameEquals(dto.getUsername()).ifPresent(user-> {throw new UserNameIsUsedException();});
        User user = userMapper.toEntity(dto);
        user.setEnabled(true);

        Set<Authority> authority = new HashSet<>();
        authorityRepository.findById(UserAuthority.ROLE_USER).ifPresent(authority::add);
        user.setAuthorities(authority);

        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);

    }
}
