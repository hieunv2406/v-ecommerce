package com.vm.adminsite.controllers;

import com.vm.dto.role.RoleRequest;
import com.vm.services.RoleService;
import com.vm.dto.ResultDto;
import com.vm.entities.Role;
import com.vm.exceptions.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<ResultDto> createNewRole(@RequestBody @Valid RoleRequest roleRequest) throws CustomException {
        log.info("create new role");
        ResultDto resultDto = roleService.save(roleRequest);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<Role>> getListRole() {
        log.info("get all roles");
        List<Role> roles = roleService.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
