package com.boot.stickershop.service.impl;

import com.boot.stickershop.domain.BasketProduct;
import com.boot.stickershop.repository.BasketProductRepository;
import com.boot.stickershop.repository.OrderRepository;
import com.boot.stickershop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    BasketProductRepository basketProductRepository;

    @Override
    public void addBasket(BasketProduct basketProduct) {
        basketProductRepository.save(basketProduct);
    }

    @Override
    public BasketProduct getBasketProduct(Long userId, Long productId) {
        return basketProductRepository.findBasketProductByUserIdAndProductId(userId, productId);
    }

    @Override
    public void updateBasketProduct(BasketProduct basketProduct) {
        basketProductRepository.saveAndFlush(basketProduct);
    }
}
