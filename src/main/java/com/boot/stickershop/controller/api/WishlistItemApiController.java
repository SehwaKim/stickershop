package com.boot.stickershop.controller.api;

import com.boot.stickershop.domain.User;
import com.boot.stickershop.domain.WishlistItem;
import com.boot.stickershop.repository.WishlistItemRepository;
import com.boot.stickershop.service.UserService;
import com.boot.stickershop.service.WishlistItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public class WishlistItemApiController {

    @Autowired
    WishlistItemService wishlistItemService;

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public String add(@RequestBody Long id){
        System.out.println("id="+id);
        return "add OK";
    }

    @PostMapping("/delete")
    public String delete(Principal principal, @RequestParam Long id){
        User user = userService.getUserByEmail(principal.getName());

        System.out.println("Id = "+id);
        //WishlistItem wishlistItem = wishlistItemService.selectOne(id);
        wishlistItemService.deleteWishlist(id);
        return "delete OK";
    }

    @PutMapping
    public WishlistItem update(@RequestParam("id") Long id){
        WishlistItem wishlistItem = new WishlistItem();
        System.out.println(id);
        return wishlistItem;
    }

}
