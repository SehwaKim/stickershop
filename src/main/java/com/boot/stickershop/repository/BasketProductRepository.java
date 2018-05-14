package com.boot.stickershop.repository;

import com.boot.stickershop.base.JpaQueryDslPredicateRepository;
import com.boot.stickershop.domain.BasketProduct;
import com.boot.stickershop.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BasketProductRepository extends JpaQueryDslPredicateRepository<BasketProduct, Long> {
    BasketProduct findBasketProductByUserIdAndProductId(Long userId, Long productId);

    @Query("SELECT b FROM BasketProduct b WHERE b.user.id = :id")
    List<BasketProduct> findAllByUserId(@Param("id") Long id);
}
