package com.jack.store.repository;

import com.jack.store.domain.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {
}
