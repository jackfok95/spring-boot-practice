package com.jack.store.security.jwt;

import com.jack.store.exception.UserNotActivatedException;
import com.jack.store.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.stream.Collectors;

@Service
public class DomainUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findUserByUsernameEquals(username).map(this::createUserDetail).orElseThrow(EntityNotFoundException::new);

    }

    private User createUserDetail(com.jack.store.domain.User user) {

        if (!user.getEnabled()) {
            throw new UserNotActivatedException(user.getUsername());
        }

        return new User(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList())
        );

    }

}