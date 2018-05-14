package com.boot.stickershop.controller.api;

import com.boot.stickershop.service.BasketProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/basket")
public class BasketApiController {

    @Autowired
    BasketProductService basketService;
    // 장바구니 비우기
    @PostMapping("/deleteAll")
    public String delete(Principal principal){
        // 회원이면 해당 회원 장바구니 비우기
        if(principal != null){

        } else {
            // 손님이면
        }


        return "delete OK";
    }

}
