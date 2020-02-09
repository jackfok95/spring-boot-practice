package com.jack.store.web.rest;

import com.jack.store.domain.Product;
import com.jack.store.dto.ProductDto;
import com.jack.store.security.data.UserAuthority;
import com.jack.store.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductResource extends AbstractResource<Product, ProductDto, Long>{

    private ProductService productService;

    public ProductResource(ProductService productService){

        super(productService);
        this.productService = productService;
    }

    @GetMapping("/category1")
    @PreAuthorize("hasRole(\"" + UserAuthority.ROLE_USER + "\")")
    public List<ProductDto> findByCategory1(){
        return productService.findByCategory1();
    }
}
