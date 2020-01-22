package com.jack.store.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto implements DtoInterface<Long>{

    private Long id;

    private String username;

    private String password;

    private String name;

    private String email;

    private LocalDate dateOfBirth;

    private String address;

    private String nation;

    private Boolean enabled;
}
