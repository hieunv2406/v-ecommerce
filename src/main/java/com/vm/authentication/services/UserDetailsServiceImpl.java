package com.vm.authentication.services;

import com.vm.dto.JwtUserDetails;
import com.vm.dto.RolesDto;
import com.vm.dto.UserDto;
import com.vm.entities.User;
import com.vm.entities.UserRole;
import com.vm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        UserDto userDTO = user.toInSiteDTO();
        Set<RolesDto> rolesDtos = new HashSet<>();
        List<UserRole> userRoleEntityList = user.getUserRoles();
        for (UserRole dto : userRoleEntityList) {
            rolesDtos.add(dto.getRole().toDTO());
        }
        userDTO.setRoles(rolesDtos);
        return JwtUserDetails.build(userDTO);
    }
}
