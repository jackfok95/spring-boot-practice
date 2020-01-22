package com.jack.store.service;

import com.jack.store.domain.Product;
import com.jack.store.dto.ProductDto;
import com.jack.store.mapper.ProductMapper;
import com.jack.store.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService extends AbstractService<Product, ProductDto, Long>{

    public ProductService(ProductRepository repository, ProductMapper mapper) {
        super(repository, mapper);
    }
}
