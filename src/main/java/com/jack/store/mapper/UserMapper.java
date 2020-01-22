package com.jack.store.mapper;

import com.jack.store.domain.User;
import com.jack.store.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<User, UserDto>{

    @Mapping(target = "products", ignore = true)
    User toEntity(UserDto dto);

    UserDto toDto(User entity);

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
