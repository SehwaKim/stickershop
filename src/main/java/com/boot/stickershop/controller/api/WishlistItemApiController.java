package com.boot.stickershop.controller.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class WishlistItemApiController {

    @PostMapping("/add")
    public String add(Long id){
        System.out.println(id);
        return "redirect:/produts";
    }

    @PutMapping("delete")
    public String delete(){

        return "redirect:/products";
    }
}
