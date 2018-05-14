package com.boot.stickershop.service;

import com.boot.stickershop.domain.BasketProduct;

import java.util.List;

public interface BasketProductService {
    public List<BasketProduct> selectAll();
    public void addBasket(BasketProduct basketProduct);
    public BasketProduct getBasketProduct(Long userId, Long productId);
    public void updateBasketProduct(BasketProduct basketProduct);
    public List<BasketProduct> getBasket(Long id);
}
