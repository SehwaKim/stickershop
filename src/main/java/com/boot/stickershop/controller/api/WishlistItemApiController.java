package com.boot.stickershop.controller.api;

import com.boot.stickershop.domain.Product;
import com.boot.stickershop.domain.User;
import com.boot.stickershop.domain.WishlistItem;
import com.boot.stickershop.repository.WishlistItemRepository;
import com.boot.stickershop.service.ProductService;
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

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public String add(Principal principal,@RequestParam Long id){
        User user = userService.getUserByEmail(principal.getName());

        Product product = productService.getProduct(id);

        WishlistItem wishlistItem = wishlistItemService.selectOneByProduct(product);
        if(wishlistItem==null){
            wishlistItem = new WishlistItem();
            wishlistItem.setUser(user);
            wishlistItem.setProduct(product);
            wishlistItemService.addWishlist(wishlistItem);
        } else {
            return "이미 있음!";
        }
        return "add OK";
    }

    @PostMapping("/delete")
    public String delete(Principal principal, @RequestParam Long id){
        User user = userService.getUserByEmail(principal.getName());

        System.out.println("Id = "+id);
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
