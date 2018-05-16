package com.boot.stickershop.service;

import com.boot.stickershop.domain.BasketProduct;
import com.boot.stickershop.domain.User;

import java.util.List;

public interface BasketProductService {
    public List<BasketProduct> selectAll();
    public void addBasket(BasketProduct basketProduct);
    public BasketProduct getBasketProduct(Long userId, Long productId);
    public void updateBasketProduct(BasketProduct basketProduct);
    public List<BasketProduct> getBasket(Long id);
    public void deleteAllByUserId(Long id);
    public void deleteByBasketId(Long id);
}
