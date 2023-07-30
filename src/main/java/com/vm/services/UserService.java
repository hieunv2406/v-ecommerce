package com.vm.services;

import com.vm.dto.Datatable;
import com.vm.dto.ResultDto;
import com.vm.dto.user.UserDto;
import com.vm.dto.user.UserRequest;
import com.vm.dto.user.UserSearchRequest;
import com.vm.exceptions.CustomException;

import java.util.List;

public interface UserService {
    UserDto getUserinfo();

    ResultDto editUser(UserDto userDto) throws CustomException;

    ResultDto save(UserRequest userRequest);

    ResultDto update(Long id, UserRequest userRequest);

    ResultDto delete(Long id);

    UserDto getUserById(Long id);

    List<UserDto> getAllUser();

    Datatable search(UserSearchRequest userSearchRequest);
}
