package com.vm.services;

import com.vm.dto.role.RoleRequest;
import com.vm.dto.ResultDto;
import com.vm.entities.Role;
import com.vm.exceptions.CustomException;
import com.vm.repository.RoleRepository;
import com.vm.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public ResultDto save(RoleRequest roleRequest) throws CustomException {
        Optional<Role> roleChecking = roleRepository.findByCode(roleRequest.getCode());
        if (roleChecking.isPresent()) {
            throw new CustomException("Role Already Exists");
        }
        Role role = new Role();
        role.setCode(roleRequest.getCode());
        role.setName(roleRequest.getName());
        role.setDescription(roleRequest.getDescription() != null ? roleRequest.getDescription() : null);
        roleRepository.save(role);
        return new ResultDto(Constants.ResponseKey.SUCCESS);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
