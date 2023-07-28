package com.vm.services;

import com.vm.dto.ResultDto;
import com.vm.dto.UserDto;
import com.vm.exceptions.UserNotFoundException;

public interface UserService {
    UserDto getUserinfo();
    ResultDto editUser(UserDto userDto) throws UserNotFoundException;
}
