package com.boot.stickershop.repository;

import com.boot.stickershop.domain.Product;
import com.boot.stickershop.repository.custom.ProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom{
    Product findProductById(Long productId);
    @Query("select distinct p from Product p left join fetch p.productFiles")
    List<Product> fetchProducts();
}
