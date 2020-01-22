package com.jack.store.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class ProductDto implements DtoInterface<Long>{

    private Long id;

    private Long userId;

    private Long categoryId;

    private String name;

    private String description;

    private Long qty;

    private BigDecimal price;

    private Boolean isSoldOut;

    private Instant bestBefore;
}
