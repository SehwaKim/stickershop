package com.boot.stickershop.service;

import com.boot.stickershop.domain.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<Product> getProductList(Long categoryId, int page, String sort, String keyword, Integer minPrice, Integer maxPrice);
}
