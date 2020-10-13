package com.jack.store.service;

import com.jack.store.domain.Category_;
import com.jack.store.domain.Product;
import com.jack.store.domain.Product_;
import com.jack.store.dto.ProductDto;
import com.jack.store.mapper.ProductMapper;
import com.jack.store.repository.ProductRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService extends AbstractService<Product, ProductDto, Long>{

    private ProductRepository repository;

    private ProductMapper mapper;

    public ProductService(ProductRepository repository, ProductMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ProductDto> findByCategory1(){

        Specification<Product> specification = (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.join(Product_.CATEGORY).get(Category_.ID), 1);

        return repository.findAll(specification).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void buy(){
//        repository.atomicUpdate();
        repository.findById(3L).ifPresent(product -> {
            product.setQty(product.getQty() - 1);
        });
    }
}
