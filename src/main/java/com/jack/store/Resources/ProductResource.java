package com.jack.store.Resources;

import com.jack.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductResource {

    @Autowired
    private ProductRepository productRepository;
}
