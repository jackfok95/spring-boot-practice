package com.jack.store.Resources;

import com.jack.store.domain.BaseModel;
import com.jack.store.dto.DtoInterface;
import com.jack.store.service.AbstractService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;

public abstract class AbstractResource<
        E extends BaseModel<ID>,
        DTO extends DtoInterface<ID>,
        ID extends Serializable> {

    private AbstractService<E, DTO, ID> service;

    public AbstractResource(AbstractService<E, DTO, ID> service){

        this.service = service;
    }

    @GetMapping()
    public Page<DTO> index (Pageable pageable){

        return service.getAllByPage(pageable);
    }

    @GetMapping("/{id}")
    public DTO get (@PathVariable ID id){

        return service.findById(id).orElse(null);
    }

    @PostMapping()
    public DTO create(@Valid @RequestBody DTO dto){

        //TODO create exception
//        if(dto.getId() != null){
//
//        }
        return service.create(dto);
    }

    @DeleteMapping("/{id")
    public void delete(@PathVariable ID id){

        service.delete(id);
    }

//TODO update function

}
