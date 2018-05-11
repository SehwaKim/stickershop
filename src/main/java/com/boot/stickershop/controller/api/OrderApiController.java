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
            String productId = basketItem.getProductId().toString();
            if(session.getAttribute(productId) == null){
                session.setAttribute(productId, basketItem.getQuantity());
            }else {
                int quantity = (int) session.getAttribute(productId);
                quantity += basketItem.getQuantity();
                session.setAttribute(productId, quantity);
            }

            System.out.println("수량 : "+session.getAttribute(productId));

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
