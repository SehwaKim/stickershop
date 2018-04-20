package com.boot.stickershop.repository;

import com.boot.stickershop.base.JpaQueryDslPredicateRepository;
import com.boot.stickershop.domain.User;

public interface UserRepository extends JpaQueryDslPredicateRepository<User, Long> {
    public User findUsersByEmail(String email);
}
