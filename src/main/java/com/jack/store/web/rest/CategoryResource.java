package com.jack.store.web.rest;

import com.jack.store.criteria.CategoryCriteria;
import com.jack.store.domain.Category;
import com.jack.store.dto.CategoryDto;
import com.jack.store.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/criteria")
    public Page<CategoryDto> getByCriteria(CategoryCriteria criteria, Pageable pageable){
        return categoryService.findByCriteria(criteria, pageable);
    }

}
