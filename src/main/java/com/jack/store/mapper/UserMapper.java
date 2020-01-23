package com.jack.store.mapper;

import com.jack.store.domain.User;
import com.jack.store.dto.UserDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface UserMapper extends EntityMapper<User, UserDto>{

    @Mapping(target = "products", ignore = true)
    User toEntity(UserDto dto);

    UserDto toDto(User entity);

    @InheritConfiguration
    User toEntity(UserDto dto, @MappingTarget User user);

    default User fromId(Long id){

        if(id == null){
            return null;
        }else{
            User user = new User();
            user.setId(id);
            return user;
        }
    }
}
