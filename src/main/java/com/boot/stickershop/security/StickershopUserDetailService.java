package com.boot.stickershop.security;

import com.boot.stickershop.domain.User;
import com.boot.stickershop.domain.UserRole;
import com.boot.stickershop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class StickershopUserDetailService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(username);
        List<GrantedAuthority> list = new ArrayList<>();
        for(UserRole userRole : user.getRoles()){
            list.add(new SimpleGrantedAuthority("ROLE_"+userRole.getRoleName()));
        }
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), list);

        return userDetails;
    }
}
