package com.boot.stickershop.service;

import com.boot.stickershop.domain.User;
import com.boot.stickershop.domain.UserConnection;

public interface UserService {
    public User addUser(User user); // 회원가입
    public User getUserByEmail(String email); // 로그인

    public User getSocialUser(String type, String providerUserId);
    public UserConnection addUserConnection(UserConnection userConnection);
}
