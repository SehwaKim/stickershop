package com.boot.stickershop.service;

import com.boot.stickershop.domain.BasketProduct;

public interface OrderService {
    void addBasket(BasketProduct basketProduct);
    BasketProduct getBasketProduct(Long userId, Long productId);
    void updateBasketProduct(BasketProduct basketProduct);
}
