package com.boot.stickershop.service.impl;

import com.boot.stickershop.domain.User;
import com.boot.stickershop.repository.UserRepository;
import com.boot.stickershop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User getUserByEmail(String email) {
        return userRepository.findUsersByEmail(email);
    }
}
