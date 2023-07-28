package com.vm.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
@Data
public class UserRequest {

    @NotEmpty(message = "{validation.user.username.notnull}")
    private String username;
    @NotEmpty(message = "{validation.user.password.notnull}")
    private String password;
    private String firstName;
    private String lastName;
    @NotEmpty(message = "{validation.user.email.notnull}")
    private String email;
    private String phoneNumber;
    private String address;
    private String[] roleInputList;
}
