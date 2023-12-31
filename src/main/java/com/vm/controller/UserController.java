package com.vm.controller;

import com.vm.dto.ResultDto;
import com.vm.dto.user.UserDto;
import com.vm.exceptions.CustomException;
import com.vm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/info")
    public ResponseEntity<UserDto> getUserInfo() {
        UserDto userInfo = userService.getUserinfo();
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    @PutMapping(path = "/edit")
    public ResponseEntity<ResultDto> editUser(@RequestBody UserDto userDto) throws CustomException {
        ResultDto resultDto = userService.editUser(userDto);
        return new ResponseEntity<>(new ResultDto(), HttpStatus.OK);
    }
}
