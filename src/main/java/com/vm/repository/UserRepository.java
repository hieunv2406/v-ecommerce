package com.vm.repository;

import com.vm.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Optional<User> findByUsername(String username);

    Optional<User> findByUserId(Long userId);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
