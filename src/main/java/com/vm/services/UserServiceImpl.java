package com.vm.services;

import com.vm.dto.ResultDto;
import com.vm.dto.UserDto;
import com.vm.entities.User;
import com.vm.exceptions.UserNotFoundException;
import com.vm.repository.UserRepository;
import com.vm.utils.AuthUtil;
import com.vm.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto getUserinfo() {
        String username = AuthUtil.getUsernameFromJwtToken();
        Optional<User> userInfo = userRepository.findByUsername(username);
        return userInfo.map(User::toDTO).orElse(null);
    }

    @Override
    public ResultDto editUser(UserDto userDto) throws UserNotFoundException {
        String username = AuthUtil.getUsernameFromJwtToken();
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User not found");
        }
        user.get().setFirstName(userDto.getFirstName());
        user.get().setLastName(userDto.getLastName());
        user.get().setEmail(userDto.getEmail());
        user.get().setAddress(userDto.getAddress());
        user.get().setPhoneNumber(userDto.getPhoneNumber());
        userRepository.save(user.get());
        return new ResultDto(Constants.ResponseKey.SUCCESS);
    }
}
