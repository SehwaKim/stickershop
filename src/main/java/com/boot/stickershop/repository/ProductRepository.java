package com.boot.stickershop.repository;

import com.boot.stickershop.domain.Product;
import com.boot.stickershop.repository.custom.ProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom{
    Product findProductById(Long productId);
}
