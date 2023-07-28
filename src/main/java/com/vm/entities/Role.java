package com.vm.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vm.dto.RolesDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    private Long roleId;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @JsonManagedReference
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> userRoles;

    public Role(Long roleId, String code, String name, String description) {
        this.roleId = roleId;
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public RolesDto toDTO() {
        return new RolesDto(
                roleId,
                code,
                name,
                description
        );
    }

}
