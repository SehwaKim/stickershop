package com.boot.stickershop.controller.api;

import com.boot.stickershop.domain.BasketProduct;
import com.boot.stickershop.domain.User;
import com.boot.stickershop.domain.WishlistItem;
import com.boot.stickershop.dto.BasketItem;
import com.boot.stickershop.service.BasketProductService;
import com.boot.stickershop.service.ProductService;
import com.boot.stickershop.service.UserService;
import com.boot.stickershop.service.WishlistItemService;
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
    BasketProductService basketProductService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    WishlistItemService wishlistItemService;

    @PostMapping
    public String addBasketProduct(@RequestBody BasketItem basketItem, Principal principal, HttpSession session){
        if(principal == null){
            if(session.getAttribute("basket") == null){
                // 새 basket 생성
                Map<Long, Integer> basket = new HashMap<>();
                basket.put(basketItem.getProductId(), basketItem.getQuantity());
                session.setAttribute("basket", basket);
            }else {
                // basket 이미 존재, 해당 상품이 이미 담겼는지 조사
                Map<Long, Integer> basket = (Map) session.getAttribute("basket");

                if(basket.containsKey(basketItem.getProductId())){
                    int quantity = basket.get(basketItem.getProductId());
                    quantity += basketItem.getQuantity();
                    basket.put(basketItem.getProductId(), quantity);
                    session.setAttribute("basket", basket);
                }else{
                    basket.put(basketItem.getProductId(), basketItem.getQuantity());
                    session.setAttribute("basket", basket);
                }
            }
        }else{
            User user = userService.getUserByEmail(principal.getName());

            BasketProduct basketProduct = basketProductService.getBasketProduct(user.getId(), basketItem.getProductId());
            if(basketProduct != null){
                // 수량 update
                int quantity = basketProduct.getQuantity();
                quantity += basketItem.getQuantity();
                basketProduct.setQuantity(quantity);
                basketProductService.updateBasketProduct(basketProduct);

            }else{
                // save
                basketProduct = new BasketProduct();
                basketProduct.setUser(user);
                basketProduct.setProduct(productService.getProduct(basketItem.getProductId()));
                basketProduct.setQuantity(basketItem.getQuantity());
                basketProductService.addBasket(basketProduct);
            }

            // 위시리스트 삭제
            WishlistItem wishlistItem = wishlistItemService.selectOneByProductIdAndUserId(basketItem.getProductId(), user.getId());
            if(wishlistItem != null) {
                wishlistItemService.deleteWishlist(wishlistItem.getId());
            }
        }
        return "ok";
    }

    @DeleteMapping
    public String deleteAll(Principal principal, HttpSession session){
        if(principal != null){
            User user = userService.getUserByEmail(principal.getName());
            basketProductService.deleteAllByUserId(user.getId());
        }else {
            session.removeAttribute("basket");
        }
        return "ok";
    }

    @DeleteMapping(value = "/{productId}")
    public int delete(@PathVariable(value = "productId") Long productId, Principal principal, HttpSession session){
        int basketSize = 0;
        if(principal != null){
            User user = userService.getUserByEmail(principal.getName());
            basketProductService.deleteByBasketId(productId);
            basketSize = basketProductService.getBasket(user.getId()).size();
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

    @PutMapping
    public void update(@RequestParam Long productId, @RequestParam Integer quantity, Principal principal, HttpSession session){
        if(principal!=null){
            User user = userService.getUserByEmail(principal.getName());
            BasketProduct basketProduct = basketProductService.getBasketProduct(user.getId(), productId);
            basketProduct.setQuantity(quantity);
            basketProductService.addBasket(basketProduct);
        }else{
            Map<Long, Integer> basket = (Map) session.getAttribute("basket");
            if(basket != null && basket.size() > 0){
                if(basket.containsKey(productId)){
                    basket.put(productId, quantity);
                    session.setAttribute("basket", basket);
                }
            }
        }
    }
}
