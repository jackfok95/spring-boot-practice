package com.jack.store.repository;

import com.jack.store.domain.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {



    @Modifying( clearAutomatically=true, flushAutomatically=true )
    @Query("update Product p set p.qty = p.qty - 1 where p.id = 3")
    void atomicUpdate();
}
