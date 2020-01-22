package com.jack.store.mapper;

import com.jack.store.domain.Product;
import com.jack.store.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CategoryMapper.class})
public interface ProductMapper extends EntityMapper<Product, ProductDto>{

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "categoryId", target = "category")
    Product toEntity(ProductDto dto);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product entity);

    default Product fromId(Long id){

        if(id == null){
            return null;
        }else{
            Product product = new Product();
            product.setId(id);
            return product;
        }
    }
}
