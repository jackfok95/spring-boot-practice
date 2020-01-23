package com.jack.store.web.rest;

import com.jack.store.domain.Category;
import com.jack.store.dto.CategoryDto;
import com.jack.store.service.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryResource extends AbstractResource<Category, CategoryDto, Long>{

    private CategoryService categoryService;

    public CategoryResource(CategoryService categoryService){
        super(categoryService);
        this.categoryService = categoryService;
    }


}
