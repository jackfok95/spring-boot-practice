package com.jack.store.repository;

import com.jack.store.domain.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Long> {
}
