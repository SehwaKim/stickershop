package com.boot.stickershop.controller;

import com.boot.stickershop.domain.User;
import com.boot.stickershop.domain.UserRole;
import com.boot.stickershop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/join")
    public String joinform(ModelMap modelMap){
        User user = new User();
        modelMap.addAttribute("user", user);

        return "users/joinform";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute User user){
        // 비밀번호 암호화
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 권한 부여
        List<UserRole> list = new ArrayList<>();
        UserRole userRole = new UserRole();
        userRole.setRoleName("USER");
        list.add(userRole);
        user.setRoles(list);

        // insert
        userService.addUser(user);
        System.out.println(user.getId());
        System.out.println(user.getRoles());

        System.out.println(user.getRegtime());

        return "users/login";
    }

    @GetMapping("login")
    public String login(){
        return "/";
    }

    // Q. 왜 로그인 후 자동으로 "/" 여기로 갈까? 아무설정 안해줬는데...
}
