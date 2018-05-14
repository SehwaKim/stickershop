package com.boot.stickershop.controller;

import com.boot.stickershop.domain.BasketProduct;
import com.boot.stickershop.domain.Order;
import com.boot.stickershop.domain.OrderProduct;
import com.boot.stickershop.domain.User;
import com.boot.stickershop.dto.BasketItem;
import com.boot.stickershop.dto.OrderSearch;
import com.boot.stickershop.service.OrderService;
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

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

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
    public String basket(Principal principal, ModelMap modelMap){
        User user = userService.getUserByEmail(principal.getName());
        List<BasketProduct> list = orderService.getBasket(user.getId());

        modelMap.addAttribute("list", list);

        return "/orders/basket";
    }
}
