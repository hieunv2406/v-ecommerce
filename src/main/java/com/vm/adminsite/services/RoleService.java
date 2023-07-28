package com.vm.adminsite.services;

import com.vm.adminsite.data.dto.RoleRequest;
import com.vm.dto.ResultDto;
import com.vm.entities.Role;
import com.vm.exceptions.RoleExistedException;

import java.util.List;

public interface RoleService {
    ResultDto createNewRole(RoleRequest roleRequest) throws RoleExistedException;

    List<Role> getListRole();
}
