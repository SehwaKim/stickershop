package com.boot.stickershop.repository;

import com.boot.stickershop.domain.User;
import com.boot.stickershop.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    public UserRole findUserRoleByRoleName(String roleName);
}
