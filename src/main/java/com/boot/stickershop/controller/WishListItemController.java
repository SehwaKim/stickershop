package com.boot.stickershop.controller;

import com.boot.stickershop.domain.Product;
import com.boot.stickershop.domain.User;
import com.boot.stickershop.domain.WishlistItem;
import com.boot.stickershop.repository.WishlistItemRepository;
import com.boot.stickershop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/users")
public class WishListItemController {
    @Autowired
    WishlistItemRepository wishlistItemRepository;

    @Autowired
    UserService userService;

    @GetMapping("/list")
    public String list(Model model, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());

        List<WishlistItem> wishlistItems = wishlistItemRepository.findWishlistItemsById(user.getId());

        WishlistItem item = new WishlistItem();
        item.setId(1L);

        item.setUser(user);
        Product product = new Product();
        product.setName("JAVA");
        product.setId(1L);
        item.setProduct(product);
        wishlistItems.add(item);

        WishlistItem item2 = new WishlistItem();
        item2.setId(2L);
        item2.setUser(user);
        Product product1 = new Product();
        product1.setName("C++");
        product1.setId(2L);
        item2.setProduct(product1);
        wishlistItems.add(item2);

        WishlistItem item3 = new WishlistItem();
        item3.setId(3L);
        item3.setUser(user);
        Product product2 = new Product();
        product2.setName("Spring");
        product2.setId(3L);
        item3.setProduct(product2);
        wishlistItems.add(item3);

        model.addAttribute("list",wishlistItems);

        return "wishlist/listform";
    }
}
