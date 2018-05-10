package com.boot.stickershop.repository;

import com.boot.stickershop.base.JpaQueryDslPredicateRepository;
import com.boot.stickershop.domain.Product;
import com.boot.stickershop.repository.custom.ProductRepositoryCustom;

public interface ProductRepository extends JpaQueryDslPredicateRepository<Product, Long>, ProductRepositoryCustom{
    Product findProductById(Long productId);
}
