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
@RequestMapping("/api/wishlist")
public class WishlistItemApiController {

    @Autowired
    WishlistItemService wishlistItemService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @PostMapping(value = "/{productId}")
    public String add(@PathVariable(value = "productId") Long productId, Principal principal){
        if(principal != null){
            User user = userService.getUserByEmail(principal.getName());
            Product product = productService.getProduct(productId);
            WishlistItem wishlistItem = wishlistItemService.selectOneByProduct(product);

            if(wishlistItem == null){
                wishlistItem = new WishlistItem();
                wishlistItem.setUser(user);
                wishlistItem.setProduct(product);
                wishlistItemService.addWishlist(wishlistItem);
                return "ok";
            } else {
                return "exist";
            }
        }else{
            return "guest";
        }
    }

    @DeleteMapping(value = "/{wishlistItemId}")
    public int delete(@PathVariable(value = "wishlistItemId") Long id, Principal principal){
        if(principal != null){
            User user = userService.getUserByEmail(principal.getName());
            wishlistItemService.deleteWishlist(id);
        }

        return wishlistItemService.selectAll().size();
    }

    @PutMapping
    public WishlistItem update(@RequestParam("id") Long id){
        WishlistItem wishlistItem = new WishlistItem();
        System.out.println(id);
        return wishlistItem;
    }

}
