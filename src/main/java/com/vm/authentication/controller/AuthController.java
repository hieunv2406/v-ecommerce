package com.vm.authentication.controller;

import com.vm.authentication.security.JwtAuthenticationProvider;
import com.vm.dto.*;
import com.vm.entities.Role;
import com.vm.entities.User;
import com.vm.entities.UserRole;
import com.vm.repository.RoleRepository;
import com.vm.repository.UserRepository;
import com.vm.repository.UserRolesRepository;
import com.vm.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin("http://localhost:8081")
@RestController
@RequestMapping(path = "/api/account")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRolesRepository userRolesRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping(path = "/signUp")
    public ResponseEntity<ResultDto> registerAccount(@RequestBody @Valid UserRequest userDto) {
        ResultDto resultDTO = new ResultDto();
        Map<String, String> errors = new HashMap<>();
        resultDTO.setKey(Constants.ResponseKey.SUCCESS);
        if (Boolean.TRUE.equals(userRepository.existsByUsername(userDto.getUsername()))) {
            errors.put("username", "is already taken!");
            resultDTO.setKey(Constants.ResponseKey.ERROR);
            resultDTO.setErrors(errors);
            return new ResponseEntity<>(resultDTO, HttpStatus.BAD_REQUEST);
        }
        if (Boolean.TRUE.equals(userRepository.existsByEmail(userDto.getEmail()))) {
            errors.put("email", "is already in use!");
            resultDTO.setKey(Constants.ResponseKey.ERROR);
            resultDTO.setErrors(errors);
            return new ResponseEntity<>(resultDTO, HttpStatus.BAD_REQUEST);
        }
        // Create new user's account
        final String errorContent = "Error: Role is not found.";
        Set<String> strRoles = new HashSet<>();
        Set<Role> roles = new HashSet<>();
        if (userDto.getRoleInputList() == null || userDto.getRoleInputList() != null && userDto.getRoleInputList().length == 0) {
            Role userRole = roleRepository.findByCode(ERoles.ROLE_USER.name())
                    .orElseThrow(() -> new RuntimeException(errorContent));
            roles.add(userRole);
        } else {
            Collections.addAll(strRoles, userDto.getRoleInputList());
        }
        strRoles.add(ERoles.ROLE_USER.name());
        strRoles.forEach(role -> {
            switch (role.toLowerCase(Locale.ROOT)) {
                case "admin":
                    Role adminRole = roleRepository.findByCode(ERoles.ROLE_ADMIN.name())
                            .orElseThrow(() -> new RuntimeException(errorContent));
                    roles.add(adminRole);

                    break;
                case "mod":
                    Role modRole = roleRepository.findByCode(ERoles.ROLE_MODERATOR.name())
                            .orElseThrow(() -> new RuntimeException(errorContent));
                    roles.add(modRole);

                    break;
                default:
                    Role userRole = roleRepository.findByCode(ERoles.ROLE_USER.name())
                            .orElseThrow(() -> new RuntimeException(errorContent));
                    roles.add(userRole);
            }
        });
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAddress(userDto.getAddress());
        User finalUser = userRepository.save(user);
        roles.forEach(role -> {
            UserRole userRoleEntity = new UserRole();
            userRoleEntity.setRole(role);
            userRoleEntity.setUser(finalUser);
            userRolesRepository.save(userRoleEntity);
        });


        return new ResponseEntity<>(resultDTO, HttpStatus.OK);
    }

    @PostMapping(path = "/signIn")
    public ResponseEntity<ResultDto> loginAccount(@RequestBody @Valid JwtRequest jwtRequest) {
        ResultDto resultDto = new ResultDto(Constants.ResponseKey.SUCCESS);
        Map<String, String> errors = new HashMap<>();
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtAuthenticationProvider.generateJwtToken(authentication);
            JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            resultDto.setObject(new JwtResponse(jwt,
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles));
        } catch (AuthenticationException ex) {
            resultDto.setKey(Constants.ResponseKey.ERROR);
            errors.put("error", ex.getMessage());
            resultDto.setErrors(errors);
        }
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

}
