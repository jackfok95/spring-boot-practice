package com.jack.store.mapper;

import com.jack.store.domain.Category;
import com.jack.store.dto.CategoryDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper extends EntityMapper<Category, CategoryDto>{

    @Mapping(target = "products", ignore = true)
    Category toEntity(CategoryDto dto);

    @InheritConfiguration
    Category toEntity(CategoryDto dto, @MappingTarget Category category);

    CategoryDto toDto(Category entity);

    default Category fromId(Long id){

        if(id == null){
            return null;
        }else{
            Category category = new Category();
            category.setId(id);
            return category;
        }
    }
}
