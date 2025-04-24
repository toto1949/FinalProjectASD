package com.taoufiq.project.Services;


import com.taoufiq.project.Models.Role;

public interface RoleService {
    Role findByName(String name);
}
