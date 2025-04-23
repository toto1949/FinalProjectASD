package com.taoufiq.project.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.taoufiq.project.DTOs.UserDTO;
import com.taoufiq.project.Models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO); 
}