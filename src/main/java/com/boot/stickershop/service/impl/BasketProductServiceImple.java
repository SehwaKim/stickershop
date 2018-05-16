package com.boot.stickershop.service.impl;

import com.boot.stickershop.domain.BasketProduct;
import com.boot.stickershop.domain.User;
import com.boot.stickershop.repository.BasketProductRepository;
import com.boot.stickershop.service.BasketProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class BasketProductServiceImple implements BasketProductService {

    @Autowired
    BasketProductRepository basketProductRepository;

    @Autowired
    UserServiceImpl userService;


    @Override
    public List<BasketProduct> selectAll() {
        return null;
    }

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

    @Override
    public List<BasketProduct> getBasket(Long id) {
        return basketProductRepository.findAllByUserId(id);
    }

    @Override
    public void deleteAllByUserId(Long id) {
        basketProductRepository.deleteAllByUserId(id);
    }

    @Override
    public void deleteByBasketId(Long id) {
        basketProductRepository.deleteByProductId(id);
    }


}
