package com.boot.stickershop.controller;

import com.boot.stickershop.domain.*;
import com.boot.stickershop.dto.OrderSearch;
import com.boot.stickershop.service.OrderService;
import com.boot.stickershop.service.ProductService;
import com.boot.stickershop.service.UserService;
import com.boot.stickershop.util.Pagination;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/orderform")
    public String orderform(@RequestParam(name = "productId") String[] productId, @RequestParam(name = "quantity", required = false) String quantity
                            ,@ModelAttribute Order order, Principal principal, HttpSession session, ModelMap modelMap) {

        List<BasketProduct> list = new ArrayList<>();

        if(principal != null){
            User user = userService.getUserByEmail(principal.getName());
            Order lastOrder = orderService.getOrder(user.getId());
            if(lastOrder != null){
                order.setReceiver(lastOrder.getReceiver());
                order.setZipcode(lastOrder.getZipcode());
                order.setAddr1(lastOrder.getAddr1());
                order.setAddr2(lastOrder.getAddr2());
                order.setPhone1(lastOrder.getPhone1());
                order.setPhone2(lastOrder.getPhone2());
                order.setPhone3(lastOrder.getPhone3());
            }

            if(quantity != null && !"".equals(quantity)){
                directOrder(productId[0], quantity, list);
            }else {
                list = orderService.getBasket(user.getId());

                for(String id : productId){
                    for(int i=0; i<list.size(); i++){
                        if(list.get(i).getProduct().getId() == Long.parseLong(id)){
                            list.remove(list.get(i));
                            break;
                        }
                    }
                }
            }
        }else {
            if(quantity != null && !"".equals(quantity)){
                directOrder(productId[0], quantity, list);
            }else {
                if(session.getAttribute("basket") != null){
                    Map<Long, Integer> basket = (Map) session.getAttribute("basket");
                    for(String id : productId){
                        if(basket.containsKey(Long.parseLong(id))){
                            BasketProduct basketProduct = new BasketProduct();
                            Product product = productService.getProduct(Long.parseLong(id));
                            basketProduct.setProduct(product);
                            basketProduct.setQuantity(basket.get(Long.parseLong(id)));

                            list.add(basketProduct);
                        }
                    }
                }
            }
        }

        modelMap.addAttribute("list", list);

        return "orders/orderform";
    }

    private void directOrder(String s, String quantity, List<BasketProduct> list) {
        BasketProduct basketProduct = new BasketProduct();
        Product product = productService.getProduct(Long.parseLong(s));
        basketProduct.setProduct(product);
        basketProduct.setQuantity(Integer.parseInt(quantity));

        list.add(basketProduct);
    }

    @GetMapping
    public String getOrderList(@ModelAttribute OrderSearch orderSearch, Principal principal, ModelMap modelMap){
        if(principal == null){
            User user = userService.getUserByEmail(principal.getName());
            orderSearch.setUserId(user.getId());
        }

        Page<Order> orderPage = orderService.getOrderList(orderSearch);
        Pagination pagination = new Pagination((int) orderPage.getTotalElements(), 10, orderSearch.getPage(), 5);

        modelMap.addAttribute("list", orderPage);
        modelMap.addAttribute("pager", pagination);

        return "/orders/list";
    }

    @PostMapping
    public String addOrder(@RequestParam(name = "productId") String[] productId, @ModelAttribute Order order, Principal principal, HttpSession session){
        if(principal != null){
            List<BasketProduct> list;
            User user = userService.getUserByEmail(principal.getName());
            list = orderService.getBasket(user.getId());

            for(String id : productId){
                for(int i=0; i<list.size(); i++){
                    if(list.get(i).getProduct().getId() == Long.parseLong(id)){
                        list.remove(list.get(i));
                        // TODO 장바구니에서 해당 BasketProduct 삭제 (장바구니 삭제 메소드 이용하기)
                        break;
                    }
                }
            }

            for(BasketProduct basketProduct : list){
                OrderProduct orderProduct = new OrderProduct();
                BeanUtils.copyProperties(basketProduct, orderProduct);
                orderProduct.setPrice(basketProduct.getProduct().getPrice());
                order.addOrderProducts(orderProduct);
            }

            order.setUser(user);

        }else {
            if(session.getAttribute("basket") != null){
                Map<Long, Integer> basket = (Map) session.getAttribute("basket");
                for(String id : productId){
                    if(basket.containsKey(Long.parseLong(id))){
                        OrderProduct orderProduct = new OrderProduct();
                        Product product = productService.getProduct(Long.parseLong(id));
                        orderProduct.setProduct(product);
                        orderProduct.setQuantity(basket.get(Long.parseLong(id)));
                        orderProduct.setPrice(product.getPrice());
                        order.addOrderProducts(orderProduct);
                        basket.remove(Long.parseLong(id));
                    }
                }
                session.setAttribute("basket", basket);
            }
        }

        order = orderService.insertOrder(order);

        return "orders/completed";
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
