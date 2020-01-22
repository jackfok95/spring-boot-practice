package com.jack.store.mapper;

import com.jack.store.domain.BaseModel;
import com.jack.store.dto.DtoInterface;

import java.util.List;

public interface EntityMapper<E extends BaseModel, D extends DtoInterface> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dto);

    List<D> toDto(List<E> entity);

}
