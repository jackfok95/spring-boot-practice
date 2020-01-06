package com.jack.store.repository;

import com.jack.store.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
}
