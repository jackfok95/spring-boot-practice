package com.jack.store.dto;

import java.io.Serializable;

public interface DtoInterface<ID extends Serializable> extends Serializable{

    ID getId();
}
