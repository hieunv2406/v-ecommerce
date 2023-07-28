package com.vm.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vm.dto.UserRolesDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class UserRole extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userRoleId")
    private Long userRoleId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "roleId", nullable = false)
    private Role role;

    public UserRolesDto toDto() {
        return new UserRolesDto(
                userRoleId,
                user,
                role
        );
    }
}
