package com.taoufiq.project.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taoufiq.project.DTOs.AuthRequest;
import com.taoufiq.project.DTOs.AuthResponse;
import com.taoufiq.project.DTOs.UserDTO;
import com.taoufiq.project.Mappers.UserMapper;
import com.taoufiq.project.Models.Role;
import com.taoufiq.project.Models.User;
import com.taoufiq.project.Security.JWTManagementUtilityService;
import com.taoufiq.project.Services.RoleService;
import com.taoufiq.project.Services.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTManagementUtilityService jwtManagementUtilityService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final RoleService roleService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          JWTManagementUtilityService jwtManagementUtilityService,
                          UserService userService, 
                          UserMapper userMapper,
                          RoleService roleService) {
        this.authenticationManager = authenticationManager;
        this.jwtManagementUtilityService = jwtManagementUtilityService;
        this.userService = userService;
        this.userMapper = userMapper;
        this.roleService = roleService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(),
                    authRequest.getPassword()
                )
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(new AuthResponse("Invalid username or password"));
        }

        String token = jwtManagementUtilityService.generateToken(authRequest.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        try {
            if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) {
                return ResponseEntity.badRequest().body("Email is required");
            }

            User user = new User();
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            user.setEmail(userDTO.getEmail());

            if (userDTO.getRoles() == null || userDTO.getRoles().isEmpty()) {
                Role defaultRole = roleService.findByName("ROLE_USER");
                user.setRoles(List.of(defaultRole));
            } else {
                List<Role> roles = userDTO.getRoles().stream()
                    .map(roleDTO -> roleService.findByName(roleDTO.getName()))
                    .collect(Collectors.toList());
                user.setRoles(roles);
            }

            userService.registerUser(user);

            return ResponseEntity.ok("Registration successful");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Registration failed: " + e.getMessage());
        }
    }
}