package com.taoufiq.project.DTOs;

import java.util.LinkedList;
import java.util.List;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private List<String> roleNames;

    public List<String> getRoleNames() {
        if (roleNames == null) {
            roleNames = new LinkedList<>();
        }
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }
}
