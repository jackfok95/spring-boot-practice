package com.jack.store.repository;

import com.jack.store.domain.Product;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {

    /**
     *
     *  Modifying query changes entities contained in the persistence context, making it inconsistent with DB data.
     *
     *  clearAutomatically => clear all entities in the context (1st level cache)
     *  flushAutomatically => flush any managed entities before executing the query. Ensure everything is persist to db
     *
     */
    @Modifying( clearAutomatically=true, flushAutomatically=true )
    @Query("delete from Product p where p.user.id = :id")
    void deleteAllByUserId(Long id);

    @Query("select p from Product p join fetch p.user u where p.id =:id")
    Optional<Product> getProductJoinFetch(@Param("id") Long id);


    @Modifying( clearAutomatically=true, flushAutomatically=true )
    @Query("update Product p set p.qty = p.qty - 1 where p.id = 3")
    void minusWithAtomic();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    // added for update to the SQL.
    // id is index, so will only lock this row. If criteria is not index, will lock the whole table
    @Query(value="select p from Product p where p.id = 3")
    List<Product> findByForUpdate ();


}
