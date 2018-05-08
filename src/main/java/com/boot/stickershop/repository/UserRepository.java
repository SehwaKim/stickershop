package com.boot.stickershop.repository;

import com.boot.stickershop.base.JpaQueryDslPredicateRepository;
import com.boot.stickershop.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaQueryDslPredicateRepository<User, Long> {
    public User findUsersByEmail(String email);

    @Query("SELECT uc.user FROM UserConnection uc WHERE uc.providerId = " +
            ":type and uc.providerUserId = :providerUserId")
    public User getSocialUser(@Param("type") String type, @Param("providerUserId") String providerUserId);
}
