package com.boot.stickershop.controller;

import com.boot.stickershop.domain.*;
import com.boot.stickershop.dto.BasketItem;
import com.boot.stickershop.dto.OrderSearch;
import com.boot.stickershop.service.OrderService;
import com.boot.stickershop.service.ProductService;
import com.boot.stickershop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @GetMapping
    public String getOrderList(@ModelAttribute OrderSearch orderSearch, Principal principal, ModelMap modelMap){
        User user = userService.getUserByEmail(principal.getName());
        // TODO 비회원 주문일 경우

        Page<Order> list = orderService.getOrderList(orderSearch);

        modelMap.addAttribute("list", list);

        return "/order/list";
    }

    @PostMapping
    public String addOrder(@ModelAttribute List<BasketItem> basketItems, Principal principal){
        if(principal.getName() != null){

        }

        return "order/completed";
    }

    @GetMapping("/basket")
    public String basket(Principal principal, ModelMap modelMap, HttpSession session){
        List<BasketProduct> list = new ArrayList<>();

        if(principal == null){
            if(session.getAttribute("basket") != null){
                Map<Long, Integer> basket = (Map) session.getAttribute("basket");

                for(Long productId : basket.keySet()){
                    int quantity = basket.get(productId);

                    BasketProduct basketProduct = new BasketProduct();
                    Product product = productService.getProduct(productId);
                    basketProduct.setProduct(product);
                    basketProduct.setQuantity(quantity);

                    list.add(basketProduct);
                }
            }
        }else {
            User user = userService.getUserByEmail(principal.getName());
            list = orderService.getBasket(user.getId());
        }

        modelMap.addAttribute("list", list);

        return "/orders/basket";
    }
}
