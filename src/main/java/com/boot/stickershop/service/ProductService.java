package com.boot.stickershop.service;

import com.boot.stickershop.domain.Product;
import com.boot.stickershop.dto.ProductSearch;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<Product> getProductList(ProductSearch productSearch);
}
