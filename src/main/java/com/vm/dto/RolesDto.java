package com.vm.dto;

import com.vm.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolesDto {
    private Long roleId;
    private String code;
    private String name;
    private String description;
    private String rolesInput;

    public RolesDto(Long roleId, String code, String name, String description) {
        this.roleId = roleId;
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public Role toEntity() {
        return new Role(
                roleId,
                code,
                name,
                description
        );
    }
}
