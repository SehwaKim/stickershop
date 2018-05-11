package com.boot.stickershop.controller.api;

import com.boot.stickershop.domain.BasketProduct;
import com.boot.stickershop.domain.Product;
import com.boot.stickershop.domain.User;
import com.boot.stickershop.dto.BasketItem;
import com.boot.stickershop.service.OrderService;
import com.boot.stickershop.service.ProductService;
import com.boot.stickershop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderApiController {
    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @PostMapping("/basket")
    public String addBasket(@RequestBody BasketItem basketItem, Principal principal, HttpSession session){
        if(principal == null){
            if(session.getAttribute("basket") == null){
                // 새 basket 생성
                Map<Long, Integer> basket = new HashMap<>();
                basket.put(basketItem.getProductId(), basketItem.getQuantity());

                System.out.println("상품id : "+basketItem.getProductId() +", 수량 : "+basket.get(basketItem.getProductId()));
                session.setAttribute("basket", basket);
            }else {
                // basket 이미 존재, 해당 상품이 이미 담겼는지 조사
                Map<Long, Integer> basket = (Map) session.getAttribute("basket");
                if(basket.containsKey(basketItem.getProductId())){
                    int quantity = basket.get(basketItem.getProductId());
                    quantity += basketItem.getQuantity();
                    basket.put(basketItem.getProductId(), quantity);

                    System.out.println("상품id : "+basketItem.getProductId() +", 수량 : "+basket.get(basketItem.getProductId()));

                    session.setAttribute("basket", basket);
                }else{
                    basket.put(basketItem.getProductId(), basketItem.getQuantity());
                    System.out.println("상품id : "+basketItem.getProductId() +", 수량 : "+basket.get(basketItem.getProductId()));

                    session.setAttribute("basket", basket);
                }
            }

        }else{
            User user = userService.getUserByEmail(principal.getName());

            BasketProduct basketProduct = orderService.getBasketProduct(user.getId(), basketItem.getProductId());
            if(basketProduct != null){
                // 수량 update
                int quantity = basketProduct.getQuantity();
                quantity += basketItem.getQuantity();
                basketProduct.setQuantity(quantity);
                orderService.updateBasketProduct(basketProduct);

            }else{
                // save
                basketProduct = new BasketProduct();
                basketProduct.setUser(user);
                basketProduct.setProduct(productService.getProduct(basketItem.getProductId()));
                basketProduct.setQuantity(basketItem.getQuantity());
                orderService.addBasket(basketProduct);
            }
        }

        //Product product = productService.getProduct(basketItem.getProductId());
        //System.out.println(product);

        return "OK";
    }
}
