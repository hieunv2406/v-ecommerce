package com.vm.adminsite.services;

import com.vm.adminsite.data.dto.RoleRequest;
import com.vm.dto.ResultDto;
import com.vm.entities.Role;
import com.vm.exceptions.RoleExistedException;
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
    public ResultDto createNewRole(RoleRequest roleRequest) throws RoleExistedException {
        Optional<Role> roleChecking = roleRepository.findByCode(roleRequest.getCode());
        if (roleChecking.isPresent()) {
            throw new RoleExistedException("Role existed");
        }
        Role role = new Role();
        role.setCode(roleRequest.getCode());
        role.setName(roleRequest.getName());
        role.setDescription(roleRequest.getDescription() != null ? roleRequest.getDescription() : null);
        roleRepository.save(role);
        return new ResultDto(Constants.ResponseKey.SUCCESS);
    }

    @Override
    public List<Role> getListRole() {
        return roleRepository.findAll();
    }
}
