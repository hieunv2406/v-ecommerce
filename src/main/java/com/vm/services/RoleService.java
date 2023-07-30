package com.vm.services;

import com.vm.dto.role.RoleRequest;
import com.vm.dto.ResultDto;
import com.vm.entities.Role;
import com.vm.exceptions.CustomException;

import java.util.List;

public interface RoleService {
    ResultDto save(RoleRequest roleRequest) throws CustomException;

    List<Role> findAll();
}
