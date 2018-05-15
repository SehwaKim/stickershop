package com.boot.stickershop.controller.api;

import com.boot.stickershop.domain.User;
import com.boot.stickershop.service.BasketProductService;
import com.boot.stickershop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RestController
@RequestMapping("/api/basket")
public class BasketApiController {

    @Autowired
    BasketProductService basketService;

    @Autowired
    UserService userService;

    // 장바구니 비우기
    @DeleteMapping("/deleteAll")
    public String deleteAll(Principal principal){
        // 회원이면 해당 회원 장바구니 비우기
        if(principal != null){
            User user = userService.getUserByEmail(principal.getName());
            basketService.deleteAllByUserId(user.getId());
        } else {
            // 손님이면
        }
        return "delete OK";
    }

    @PostMapping("/delete")
    public String delete(Principal principal,@RequestParam Long id){
        if(principal!=null){
            User user = userService.getUserByEmail(principal.getName());
            basketService.deleteByBasketId(id);
            return "delete OK";
        }
        return "Not User!";
    }

}
