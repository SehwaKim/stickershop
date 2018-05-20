package com.boot.stickershop.controller.api;

import com.boot.stickershop.domain.BasketProduct;
import com.boot.stickershop.domain.User;
import com.boot.stickershop.service.BasketProductService;
import com.boot.stickershop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("/api/basket")
public class BasketApiController {

    @Autowired
    BasketProductService basketService;

    @Autowired
    UserService userService;

    @DeleteMapping
    public String deleteAll(Principal principal, HttpSession session){
        if(principal != null){
            User user = userService.getUserByEmail(principal.getName());
            basketService.deleteAllByUserId(user.getId());
        }else {
            Map<Long, Integer> basket = new HashMap<>();
            session.setAttribute("basket", basket);
        }
        return "ok";
    }

    @DeleteMapping(value = "/{productId}")
    public int delete(@PathVariable(value = "productId") Long productId, Principal principal, HttpSession session){
        int basketSize = 0;
        if(principal != null){
            User user = userService.getUserByEmail(principal.getName());
            basketService.deleteByBasketId(productId);
            basketSize = basketService.getBasket(user.getId()).size();
        }else {
            Map<Long, Integer> basket = (Map) session.getAttribute("basket");
            if(basket != null && basket.size() > 0){
                if(basket.containsKey(productId)){
                    basket.remove(productId);
                    session.setAttribute("basket", basket);
                    basketSize = basket.size();
                }
            }
        }
        return basketSize;
    }

    @PutMapping("/update")
    public String update(Principal principal, @RequestParam Long id, @RequestParam int quantity){
        if(principal!=null){
            User user = userService.getUserByEmail(principal.getName());
            BasketProduct product = basketService.getBasketProduct(user.getId(),id);
            product.setQuantity(quantity);
            basketService.addBasket(product);
            return "update OK";
        }
        return "update Error";
    }
}
