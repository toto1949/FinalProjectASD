package com.taoufiq.project.DTOs;

import java.util.LinkedList;
import java.util.List;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private List<String> roleNames;

    // Getter for roleNames to avoid null
    public List<String> getRoleNames() {
        if (roleNames == null) {
            roleNames = new LinkedList<>(); // Initialize as empty set if null
        }
        return roleNames;
    }

    // Setter for roleNames (no changes needed, unless you want additional logic)
    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }
}
