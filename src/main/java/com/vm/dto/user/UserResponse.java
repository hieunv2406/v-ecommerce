package com.vm.dto.user;

import com.vm.dto.role.RolesDto;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserResponse {
    private Long userId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private Set<RolesDto> roles = new HashSet<>();
    private String[] roleInputList;
}
