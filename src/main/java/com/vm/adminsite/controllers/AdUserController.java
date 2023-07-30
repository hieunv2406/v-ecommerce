package com.vm.adminsite.controllers;

import com.vm.dto.Datatable;
import com.vm.dto.ResultDto;
import com.vm.dto.user.UserDto;
import com.vm.dto.user.UserRequest;
import com.vm.dto.user.UserSearchRequest;
import com.vm.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/user")
public class AdUserController {

    private final UserService userService;

    public AdUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<ResultDto> createNewUser(@RequestBody UserRequest userRequest) {
        log.info("Create new User");
        ResultDto resultDto = userService.save(userRequest);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ResultDto> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        log.info("Update User by id: {}", id);
        ResultDto resultDto = userService.update(id, userRequest);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResultDto> deleteUser(@PathVariable Long id) {
        log.info("Delete User by id: {}", id);
        ResultDto resultDto = userService.delete(id);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        log.info("Get User information by id: {}", id);
        UserDto userDto = userService.getUserById(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<UserDto>> getAllUser() {
        log.info("Get all users");
        List<UserDto> userDtos = userService.getAllUser();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<Datatable> getUserPage(@RequestBody UserSearchRequest userSearchRequest) {
        log.info("get Users page");
        Datatable datatable = userService.search(userSearchRequest);
        return new ResponseEntity<>(datatable, HttpStatus.OK);
    }
}
