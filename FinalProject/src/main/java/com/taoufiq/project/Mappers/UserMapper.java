package com.taoufiq.project.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;

import com.taoufiq.project.DTOs.RoleDTO;
import com.taoufiq.project.DTOs.UserDTO;
import com.taoufiq.project.Models.Role;
import com.taoufiq.project.Models.User;

@Mapper(componentModel = "spring")
public class UserMapper {

    // Map User entity to UserDTO
    public static UserDTO toDTO(User user) {
        if (user == null) return null;

        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());

        // Role names
        // dto.setRoleNames(user.getRoles().stream()
        //         .map(Role::getName)
        //         .collect(Collectors.toList()));

        // Full RoleDTOs
        Set<RoleDTO> roles = user.getRoles().stream()
        .map(role -> new RoleDTO(role.getRoleId().longValue(), role.getName()))
        .collect(Collectors.toSet());
    dto.setRoles(roles);
    

        return dto;
    }

    // Map UserDTO to User (with provided Role entities)
    public static User toEntity(UserDTO userDTO, List<Role> roles) {
        if (userDTO == null) return null;

        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setRoles(roles);

        // Optional: Set account status defaults
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        return user;
    }
}
