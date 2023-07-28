package com.vm.adminsite.data.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RoleRequest {

    @NotEmpty(message = "{validation.role.code.notnull}")
    private String code;
    @NotEmpty(message = "{validation.role.name.notnull}")
    private String name;
    private String description;

}
