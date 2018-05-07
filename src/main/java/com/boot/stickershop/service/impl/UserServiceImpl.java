package com.boot.stickershop.service.impl;

import com.boot.stickershop.domain.User;
import com.boot.stickershop.domain.UserConnection;
import com.boot.stickershop.repository.UserConnectionRepository;
import com.boot.stickershop.repository.UserRepository;
import com.boot.stickershop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConnectionRepository userConnectionRepository;

    @Override
    @Transactional
    public User addUser(User user) {
        User saveUser = userRepository.save(user);
        return saveUser;
    }

    @Override
    @Transactional
    public User getUserByEmail(String email) {
        return userRepository.findUsersByEmail(email);
    }

    @Override
    @Transactional(readOnly = false)
    public User getSocialUser(String type, String providerUserId) {
        User user = userRepository.getSocialUser(type,providerUserId);
        return user;
    }

    @Override
    @Transactional
    public UserConnection addUserConnection(UserConnection userConnection) {
        return userConnectionRepository.save(userConnection);
    }
}
