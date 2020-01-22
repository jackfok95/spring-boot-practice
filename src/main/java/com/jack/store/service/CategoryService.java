package com.jack.store.service;

import com.jack.store.domain.Category;
import com.jack.store.dto.CategoryDto;
import com.jack.store.mapper.CategoryMapper;
import com.jack.store.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryService extends AbstractService<Category, CategoryDto, Long>{

    public CategoryService(CategoryRepository repository, CategoryMapper mapper){
        super(repository, mapper);
    }

}
