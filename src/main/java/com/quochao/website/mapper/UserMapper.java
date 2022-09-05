package com.quochao.website.mapper;

import com.quochao.website.dto.UserDto;
import com.quochao.website.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    
    User toEntity(UserDto userDto);

    UserDto toDto(User user);
}
