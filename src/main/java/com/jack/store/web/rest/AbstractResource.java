package com.jack.store.web.rest;

import com.jack.store.domain.BaseModel;
import com.jack.store.dto.DtoInterface;
import com.jack.store.security.data.UserAuthority;
import com.jack.store.service.AbstractService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;

@RestController
public abstract class AbstractResource<
        E extends BaseModel<ID>,
        DTO extends DtoInterface<ID>,
        ID extends Serializable> {

    private AbstractService<E, DTO, ID> service;

    public AbstractResource(AbstractService<E, DTO, ID> service){

        this.service = service;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping()
    public Page<DTO> index (Pageable pageable){

        return service.getAllByPage(pageable);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/{id}")
    public DTO get (@PathVariable ID id){

        return service.findById(id).orElse(null);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping()
    public DTO create(@Valid @RequestBody DTO dto){

        return service.create(dto);
    }

    @PreAuthorize("hasRole(\"" + UserAuthority.ROLE_ADMIN + "\")")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable ID id){

        service.delete(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping()
    public DTO update(@Valid @RequestBody DTO dto){

        return service.update(dto);
    }

}
