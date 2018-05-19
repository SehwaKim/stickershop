package com.boot.stickershop.controller;

import com.boot.stickershop.domain.*;
import com.boot.stickershop.dto.OrderSearch;
import com.boot.stickershop.repository.BasketProductRepository;
import com.boot.stickershop.service.BasketProductService;
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
    BasketProductService basketProductService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @PostMapping("/orderform")
    public String orderform(@RequestParam(name = "productId") List<Long> productIds, @RequestParam(name = "quantity", required = false) Integer quantity
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
                directOrder(productIds.get(0), quantity, list);
            }else {
                List<BasketProduct> tmp = basketProductService.getBasket(user.getId());

                for(Long productId : productIds){
                    for(int i=0; i<tmp.size(); i++){
                        if(tmp.get(i).getProduct().getId().equals(productId)){
                            list.add(tmp.get(i));
                            break;
                        }
                    }
                }
            }
        }else {
            if(quantity != null && !"".equals(quantity)){
                directOrder(productIds.get(0), quantity, list);
            }else {
                if(session.getAttribute("basket") != null){
                    Map<Long, Integer> basket = (Map) session.getAttribute("basket");
                    for(Long productId : productIds){
                        if(basket.containsKey(productId)){
                            BasketProduct basketProduct = new BasketProduct();
                            Product product = productService.getProduct(productId);
                            basketProduct.setProduct(product);
                            basketProduct.setQuantity(basket.get(productId));

                            list.add(basketProduct);
                        }
                    }
                }
            }
        }

        modelMap.addAttribute("list", list);

        return "orders/orderform";
    }

    private void directOrder(Long productId, @RequestParam(name = "quantity", required = false) Integer quantity, List<BasketProduct> list) {
        BasketProduct basketProduct = new BasketProduct();
        Product product = productService.getProduct(productId);
        basketProduct.setProduct(product);
        basketProduct.setQuantity(quantity);

        list.add(basketProduct);
    }

    @GetMapping
    public String getOrderList(@ModelAttribute OrderSearch orderSearch, Principal principal, ModelMap modelMap){
        if(principal == null && orderSearch.getOrderNo() == null){
            modelMap.addAttribute("isGuestAccess", true);

            return "/orders/list";
        }

        if(principal != null){
            User user = userService.getUserByEmail(principal.getName());
            orderSearch.setUserId(user.getId());
        }

        Page<Order> orderPage = orderService.getOrderList(orderSearch);
        Pagination pagination = null;

        if(orderPage != null){
            if(orderPage.getContent().size() > 0) {
                pagination = new Pagination((int) orderPage.getTotalElements(), 10, orderSearch.getPage(), 5);
            }

        }
        modelMap.addAttribute("isGuestAccess", false);
        modelMap.addAttribute("list", orderPage);
        modelMap.addAttribute("pager", pagination);
        System.out.println("----");
        List<OrderProduct> list = orderPage.getContent().get(0).getOrderProducts();
        for(OrderProduct orderProduct : list){
            System.out.println(orderProduct);
        }
        System.out.println("----");
        return "/orders/list";
    }

    @PostMapping
    public String addOrder(@RequestParam(name = "productId") List<Long> productIds, @RequestParam(name = "quantity") List<Integer> quantities, @ModelAttribute Order order, Principal principal, HttpSession session){
        if(principal != null){
            User user = userService.getUserByEmail(principal.getName());
            List<BasketProduct> basket = basketProductService.getBasket(user.getId());

            for(Long productId : productIds){
                for(int i=0; i<basket.size(); i++){
                    if(basket.get(i).getProduct().getId().equals(productId)){
                        // TODO 장바구니에서 해당 BasketProduct 삭제 (장바구니 삭제 메소드 이용하기)
                        break;
                    }
                }
            }

            generateOrderProducts(productIds, quantities, order);
            order.setUser(user);

        }else {
            if(session.getAttribute("basket") != null){
                Map<Long, Integer> basket = (Map) session.getAttribute("basket");
                for(Long productId : productIds){
                    if(basket.containsKey(productId)){
                        basket.remove(productId);
                    }
                }
                session.setAttribute("basket", basket);
            }

            generateOrderProducts(productIds, quantities, order);
        }
        order = orderService.insertOrder(order);

        return "orders/completed";
    }

    private void generateOrderProducts(@RequestParam(name = "productId") List<Long> productIds, @RequestParam(name = "quantity") List<Integer> quantities, @ModelAttribute Order order) {
        int idx=0;
        for(Long productId : productIds){
            OrderProduct orderProduct = new OrderProduct();
            Product selected = productService.getProduct(productId);
            orderProduct.setProduct(selected);
            orderProduct.setQuantity(quantities.get(idx++));
            orderProduct.setPrice(selected.getPrice());
            order.addOrderProducts(orderProduct);
        }
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
            list = basketProductService.getBasket(user.getId());
        }

        modelMap.addAttribute("list", list);

        return "/orders/basket";
    }
}
