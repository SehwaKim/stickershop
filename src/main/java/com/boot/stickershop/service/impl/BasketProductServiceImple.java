package com.boot.stickershop.service.impl;

import com.boot.stickershop.domain.BasketProduct;
import com.boot.stickershop.domain.User;
import com.boot.stickershop.repository.BasketProductRepository;
import com.boot.stickershop.service.BasketProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
public class BasketProductServiceImple implements BasketProductService {

    @Autowired
    BasketProductRepository basketProductRepository;

    @Autowired
    UserServiceImpl userService;


    @Override
    @Transactional(readOnly = true)
    public List<BasketProduct> selectAll() {
        return null;
    }

    @Override
    @Transactional
    public void addBasket(BasketProduct basketProduct) {
        basketProductRepository.save(basketProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public BasketProduct getBasketProduct(Long userId, Long productId) {
        return basketProductRepository.findBasketProductByUserIdAndProductId(userId, productId);
    }

    @Override
    @Transactional
    public void updateBasketProduct(BasketProduct basketProduct) {
        basketProductRepository.saveAndFlush(basketProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BasketProduct> getBasket(Long id) {
        return basketProductRepository.findAllByUserId(id);
    }

    @Override
    @Transactional
    public void deleteAllByUserId(Long id) {
        basketProductRepository.deleteAllByUserId(id);
    }

    @Override
    @Transactional
    public void deleteByBasketId(Long id) {
        basketProductRepository.deleteByProductId(id);
    }


}
