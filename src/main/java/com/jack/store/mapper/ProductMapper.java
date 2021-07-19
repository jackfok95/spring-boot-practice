package com.jack.store.mapper;

import com.jack.store.domain.Product;
import com.jack.store.dto.ProductDto;
import org.mapstruct.*;

@Mapper(config = MapperConfig.class, uses = {UserMapper.class, CategoryMapper.class})
public interface ProductMapper extends EntityMapper<Product, ProductDto>{

    @Mapping(source = "userId", target = "user")
    Product toEntity(ProductDto dto);

    @InheritConfiguration
    // for the field "description", allow updating it to NULL value
    @Mapping(target = "description", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    Product toEntity(ProductDto dto, @MappingTarget Product product);

    @Mapping(source = "user.id", target = "userId")
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
