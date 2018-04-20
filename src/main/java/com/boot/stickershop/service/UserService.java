package com.boot.stickershop.service;

import com.boot.stickershop.domain.User;

public interface UserService {
    public User addUser(User user); // 회원가입
    public User getUserByEmail(String email); // 로그인
}
