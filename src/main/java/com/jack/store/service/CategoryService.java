package com.jack.store.service;

import com.jack.store.criteria.CategoryCriteria;
import com.jack.store.domain.Category;
import com.jack.store.domain.Category_;
import com.jack.store.dto.CategoryDto;
import com.jack.store.mapper.CategoryMapper;
import com.jack.store.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryService extends AbstractService<Category, CategoryDto, Long>{

    private CategoryRepository repository;

    private CategoryMapper mapper;

    public CategoryService(CategoryRepository repository, CategoryMapper mapper){
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }


    public Page<CategoryDto> findByCriteria(CategoryCriteria criteria, Pageable page){

        Specification<Category> specification = createSpecification(criteria);
        return repository.findAll(specification, page).map(mapper::toDto);
    }

    protected Specification<Category> createSpecification(CategoryCriteria criteria) {

        Specification<Category> categorySpecification = (root, query, cb) -> cb.and();

        if(criteria != null){

            if(criteria.getId() != null){
                categorySpecification = categorySpecification.and(buildSpecification(criteria.getId(), Category_.id));
            }
            if(criteria.getName() != null){
                categorySpecification = categorySpecification.and(buildStringSpecification(criteria.getName(),  Category_.name));
            }
        }

        return categorySpecification;
    }
}
