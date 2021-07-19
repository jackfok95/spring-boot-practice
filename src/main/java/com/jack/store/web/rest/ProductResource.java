package com.jack.store.web.rest;

import com.jack.store.domain.Product;
import com.jack.store.dto.ProductDto;
import com.jack.store.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/product")
public class ProductResource extends AbstractResource<Product, ProductDto, Long>{

    private ProductService productService;

    public ProductResource(ProductService productService){

        super(productService);
        this.productService = productService;
    }

    // Use JoinFetch
    @GetMapping("{id}/getUserNameJoinFetch")
    public String findUserNameJoinFetch(@PathVariable Long id ){
        return productService.getUserNameByProductId(id, true);
    }
    // Do not Use JoinFetch
    @GetMapping("{id}/getUserName")
    public String findUserName(@PathVariable Long id ){
        return productService.getUserNameByProductId(id, false);
    }


    /**
     * Concurrent minus 1 to qty
     */
    @GetMapping("/minusWithConcurrentError")
    public void minusWithConcurrentError(){
        productService.minusWithConcurrentError();
    }
    @GetMapping("/minusWithAtomic")
    public void minusWithAtomic(){
        productService.minusWithAtomic();
    }
    @GetMapping("/minusWithPessimisticLock")
    public void minusWithPessimisticLock(){
        productService.minusWithPessimisticLock();
    }
    @GetMapping("/minusWithOptimistic")
    public void minusWithOptimistic(){
        try{
            productService.minusWithOptimistic();
        } catch (ObjectOptimisticLockingFailureException e){
            log.info("The record has been updated before you can!");
        }
    }

//    @GetMapping("/category1")
//    @PreAuthorize("hasRole(\"" + UserAuthority.ROLE_USER + "\")")
//    public List<ProductDto> findByCategory1(){
//        return productService.findByCategory1();
//    }
}
