package com.jack.store.repository;

import com.jack.store.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {

    Optional<User> findUserByUsernameEquals(String username);
}
