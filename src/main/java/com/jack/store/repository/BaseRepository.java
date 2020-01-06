package com.jack.store.repository;

import com.jack.store.domain.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends BaseModel, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}
