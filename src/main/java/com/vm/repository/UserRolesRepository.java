package com.vm.repository;

import com.vm.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolesRepository extends JpaRepository<UserRole, Long> {

}
