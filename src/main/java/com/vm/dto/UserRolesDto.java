package com.vm.dto;

import com.vm.entities.Role;
import com.vm.entities.User;
import com.vm.entities.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRolesDto {

    private Long id;
    private User user;
    private Role role;

    public UserRole toEntity() {
        return new UserRole(
                id,
                user,
                role
        );
    }

}
