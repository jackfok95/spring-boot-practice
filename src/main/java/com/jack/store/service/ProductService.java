package com.jack.store.service;

import com.jack.store.domain.Product;
import com.jack.store.dto.ProductDto;
import com.jack.store.mapper.ProductMapper;
import com.jack.store.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@Transactional
public class ProductService extends AbstractService<Product, ProductDto, Long>{

    private ProductRepository repository;

    private ProductMapper mapper;

    public ProductService(ProductRepository repository, ProductMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    public String getUserNameByProductId(Long id, boolean joinFetch){
        Optional<Product> product ;
        if(joinFetch){
            product = repository.getProductJoinFetch(id);
        }else{
            product = repository.findById(id);
        }
        return product.map(p ->
            p.getUser().getName()
        ).orElse("impossible");
    }

    /**
     * Concurrent minus 1 to qty
     */
    // This will throw concurrent error
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void minusWithConcurrentError(){

        Product p = repository.findById(3L).get();
        Long q = p.getQty();
        p.setQty(q-1);

    }
    // This will not throw error and be able to minus 100 with 10 threads.
    // It is because read_committed level and above has write lock when writing.
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void minusWithAtomic(){
        repository.minusWithAtomic();
    }
    // Use Pessimistic Lock to retrieve the record and do the rest of it.
    // This is bad because when one user select it, other user cannot even read it at the same time.
    @Transactional(isolation=Isolation.READ_COMMITTED)
    public void minusWithPessimisticLock(){
        repository.findByForUpdate().forEach(product->{
            product.setQty(product.getQty()-1);
        });
    }
    @Transactional(isolation = Isolation.DEFAULT)
    public void minusWithOptimistic(){

        Product p = repository.findById(3L).get();
        Long q = p.getQty();
        p.setQty(q-1);
    }


//    public List<ProductDto> findByCategory1(){

//        Specification<Product> specification = (root, criteriaQuery, criteriaBuilder) ->
//                criteriaBuilder.equal(root.join(Product_.CATEGORY).get(Category_.ID), 1);

//        return repository.findAll(specification).stream().map(mapper::toDto).collect(Collectors.toList());
//    }
}
