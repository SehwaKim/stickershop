package com.boot.stickershop.repository;

import com.boot.stickershop.base.JpaQueryDslPredicateRepository;
import com.boot.stickershop.domain.BasketProduct;

public interface BasketProductRepository extends JpaQueryDslPredicateRepository<BasketProduct, Long> {
    BasketProduct findBasketProductByUserIdAndProductId(Long userId, Long productId);
}
