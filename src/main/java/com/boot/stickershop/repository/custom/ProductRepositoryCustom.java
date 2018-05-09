package com.boot.stickershop.repository.custom;

import com.boot.stickershop.domain.Product;
import com.boot.stickershop.dto.ProductSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {
    Page<Product> getProductsByDSL(ProductSearch productSearch, Pageable pageable);
}