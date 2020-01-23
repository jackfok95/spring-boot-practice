package com.jack.store.mapper;

import com.jack.store.domain.BaseModel;
import com.jack.store.dto.DtoInterface;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface EntityMapper<E extends BaseModel, D extends DtoInterface> {

    E toEntity(D dto);

    E toEntity(D dto, @MappingTarget E entity);

    D toDto(E entity);

    List<E> toEntity(List<D> dto);

    List<D> toDto(List<E> entity);

}
