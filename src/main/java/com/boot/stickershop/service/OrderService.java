package com.boot.stickershop.service;

import com.boot.stickershop.domain.BasketProduct;
import com.boot.stickershop.domain.Order;
import com.boot.stickershop.domain.User;
import com.boot.stickershop.dto.OrderSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    void addBasket(BasketProduct basketProduct);
    BasketProduct getBasketProduct(Long userId, Long productId);
    void updateBasketProduct(BasketProduct basketProduct);
    List<BasketProduct> getBasket(Long id);
    Page<Order> getOrderList(OrderSearch orderSearch);
    Order getOrder(Long userId);
    Order insertOrder(Order order);
}
