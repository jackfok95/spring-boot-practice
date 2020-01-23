package com.jack.store.service;

import com.jack.store.domain.BaseModel;
import com.jack.store.dto.DtoInterface;
import com.jack.store.mapper.EntityMapper;
import com.jack.store.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Transactional
public abstract class AbstractService<E extends BaseModel, D extends DtoInterface<ID>, ID extends Serializable> {

    private final BaseRepository<E, ID> repository;

    private final EntityMapper<E, D> mapper;

    public AbstractService(BaseRepository<E, ID> repository, EntityMapper<E, D> mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public Optional<D> findById(ID id){

        return repository.findById(id).map(mapper::toDto);
    }

    public List<D> getAll(){

        return mapper.toDto(repository.findAll());
    }

    public Page<D> getAllByPage(Pageable pageable){

        return repository.findAll(pageable).map(mapper::toDto);
    }

    public D create(D dto){
        //TODO throw
        E entity = mapper.toEntity(dto);
        entity = repository.save(entity);

        return mapper.toDto(entity);
    }

    public void delete(ID id){

        repository.deleteById(id);
    }

    public D update(D dto){

        E entity = repository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
        entity = mapper.toEntity(dto, entity);
        entity = repository.save(entity);

        return mapper.toDto(entity);
    }

}
