package com.jack.store.service;

import com.jack.store.domain.BaseModel;
import com.jack.store.dto.DtoInterface;
import com.jack.store.mapper.EntityMapper;
import com.jack.store.repository.BaseRepository;
import com.jack.store.service.queryService.QueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
public abstract class AbstractService<E extends BaseModel, D extends DtoInterface<ID>, ID extends Serializable> extends QueryService<E> {

    private final BaseRepository<E, ID> repository;

    private final EntityMapper<E, D> mapper;

    public AbstractService(BaseRepository<E, ID> repository, EntityMapper<E, D> mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Cacheable(value="userCache",key="#p0")
    public Optional<D> findById(ID id){

        log.debug("Get to Database");
        return repository.findById(id).map(mapper::toDto);
    }

    public List<D> getAll(){

        return mapper.toDto(repository.findAll());
    }

    public Page<D> getAllByPage(Pageable pageable){

        return repository.findAll(pageable).map(mapper::toDto);
    }

    public D create(D dto){
        if (dto.getId() != null){
            throw new IllegalArgumentException("id should be none");
        }
        E entity = mapper.toEntity(dto);
        entity = repository.save(entity);

        return mapper.toDto(entity);
    }

    public void delete(ID id){

        repository.deleteById(id);
    }

    public D update(D dto){

        if (dto.getId() == null){
            throw new IllegalArgumentException("id is missing");
        }
        E entity = repository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
        entity = mapper.toEntity(dto, entity);
        entity = repository.save(entity);

        return mapper.toDto(entity);
    }

}
