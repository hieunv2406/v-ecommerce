package com.vm.services;

import com.vm.dto.Datatable;
import com.vm.dto.ResultDto;
import com.vm.dto.user.UserDto;
import com.vm.dto.user.UserRequest;
import com.vm.dto.user.UserSearchRequest;
import com.vm.entities.User;
import com.vm.exceptions.CustomException;
import com.vm.repository.UserRepository;
import com.vm.utils.AuthUtil;
import com.vm.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto getUserinfo() {
        String username = AuthUtil.getUsernameFromJwtToken();
        Optional<User> userInfo = userRepository.findByUsername(username);
        return userInfo.map(User::toDTO).orElse(null);
    }

    @Override
    public ResultDto editUser(UserDto userDto) throws CustomException {
        String username = AuthUtil.getUsernameFromJwtToken();
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new CustomException("User not found");
        }
        user.get().setFirstName(userDto.getFirstName());
        user.get().setLastName(userDto.getLastName());
        user.get().setEmail(userDto.getEmail());
        user.get().setAddress(userDto.getAddress());
        user.get().setPhoneNumber(userDto.getPhoneNumber());
        userRepository.save(user.get());
        return new ResultDto(Constants.ResponseKey.SUCCESS);
    }

    @Override
    public ResultDto save(UserRequest userRequest) {
        return null;
    }

    @Override
    public ResultDto update(Long id, UserRequest userRequest) {
        return null;
    }

    @Override
    public ResultDto delete(Long id) {
        return null;
    }

    @Override
    public UserDto getUserById(Long id) {
        return null;
    }

    @Override
    public List<UserDto> getAllUser() {
        return null;
    }

    @Override
    public Datatable search(UserSearchRequest userSearchRequest) {
        return null;
    }
}
