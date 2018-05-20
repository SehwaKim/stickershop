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
import java.util.Map;

@RestController
@RequestMapping("/api/basket")
public class BasketApiController {

    @Autowired
    BasketProductService basketService;

    @Autowired
    UserService userService;

    @DeleteMapping("/deleteAll")
    public String deleteAll(Principal principal, HttpSession session){
        if(principal != null){
            User user = userService.getUserByEmail(principal.getName());
            basketService.deleteAllByUserId(user.getId());
        }else {
            Map<Long, Integer> basket = (Map) session.getAttribute("basket");
            if(basket != null){
                for(Long productId : basket.keySet()){
                    basket.remove(productId);
                }
            }
        }
        return "ok";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long productId, Principal principal, HttpSession session){
        if(principal != null){
            User user = userService.getUserByEmail(principal.getName());
            basketService.deleteByBasketId(productId);
        }else {
            Map<Long, Integer> basket = (Map) session.getAttribute("basket");
            if(basket != null && basket.size() > 0){
                if(basket.containsKey(productId)){
                    basket.remove(productId);
                    session.setAttribute("basket", basket);
                }
            }
        }
        return "ok";
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
