package com.boot.stickershop.controller;

import com.boot.stickershop.domain.Product;
import com.boot.stickershop.domain.User;
import com.boot.stickershop.domain.WishlistItem;
import com.boot.stickershop.repository.WishlistItemRepository;
import com.boot.stickershop.service.UserService;
import com.boot.stickershop.service.WishlistItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/users")
public class WishListItemController {

    @Autowired
    UserService userService;

    @Autowired
    WishlistItemService wishlistItemService;

    @GetMapping("/list")
    public String list(Model model, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());

        List<WishlistItem> wishlistItems = wishlistItemService.selectAll();

        model.addAttribute("list",wishlistItems);

        return "wishlist/listform";
    }
}
