package com.boot.stickershop.repository;

import com.boot.stickershop.base.JpaQueryDslPredicateRepository;
import com.boot.stickershop.domain.Product;
import org.springframework.data.domain.Page;

public interface ProductRepository extends JpaQueryDslPredicateRepository<Product, Long> {
    //findByTitleContainingAndBnoGreaterThan(String keyword, Long num);
    //Page<Product>
}
