package com.jack.store.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Data
public class ProductDto implements DtoInterface<Long>{

    private Long id;

    private Long userId;

    private Set<CategoryDto> categories;

    private String name;

    private String description;

    private Long qty;

    private BigDecimal price;

    private Boolean isSoldOut;

    private Instant bestBefore;
}
