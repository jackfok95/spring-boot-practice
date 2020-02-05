package com.jack.store.criteria;

import com.jack.store.service.queryService.Criteria;
import com.jack.store.service.queryService.filter.LongFilter;
import com.jack.store.service.queryService.filter.StringFilter;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryCriteria implements Criteria, Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    @Override
    public CategoryCriteria copy() {
        return new CategoryCriteria(this);
    }

    public CategoryCriteria(){}

    public CategoryCriteria(CategoryCriteria other){

        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();

    }
}
