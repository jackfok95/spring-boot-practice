package com.jack.store.dto;

import lombok.Data;

@Data
public class CategoryDto implements DtoInterface<Long>{

    private Long id;

    private String name;
}
