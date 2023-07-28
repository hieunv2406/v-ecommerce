package com.vm.adminsite.controllers;

import com.vm.adminsite.data.dto.RoleRequest;
import com.vm.adminsite.services.RoleService;
import com.vm.dto.ResultDto;
import com.vm.entities.Role;
import com.vm.exceptions.RoleExistedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping(path = "/create")
    public ResponseEntity<ResultDto> createNewRole(@RequestBody @Valid RoleRequest roleRequest) throws RoleExistedException {
        log.info("create new role");
        ResultDto resultDto = roleService.createNewRole(roleRequest);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<Role>> getListRole() {
        log.info("get list role");
        List<Role> roles = roleService.getListRole();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
