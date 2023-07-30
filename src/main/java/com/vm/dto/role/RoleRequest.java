package com.vm.dto.role;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RoleRequest {

    @NotEmpty
    private String code;
    @NotEmpty
    private String name;
    private String description;

}
