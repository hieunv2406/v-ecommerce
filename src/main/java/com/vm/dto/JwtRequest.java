package com.vm.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class JwtRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;

    @NotEmpty(message = "{validation.user.username.notnull}")
    private String username;
    @NotEmpty(message = "{validation.user.password.notnull}")
    private String password;
}
