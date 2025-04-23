package com.taoufiq.project.DTOs;


import java.util.List;
import java.util.Set;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> roleNames;  // List of role names (not role objects)
    private Set<RoleDTO> roles;  // Set of role DTOs, if you need to pass full role details
}