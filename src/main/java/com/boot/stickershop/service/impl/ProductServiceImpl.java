package com.boot.stickershop.service.impl;

import com.boot.stickershop.domain.Product;
import com.boot.stickershop.dto.ProductSearch;
import com.boot.stickershop.repository.ProductRepository;
import com.boot.stickershop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> getProductList(ProductSearch productSearch) {
        Page<Product> list = null;
        PageRequest pageRequest = null;

        if("FAVOR".equals(productSearch.getSort().toUpperCase())){
            pageRequest = PageRequest.of(productSearch.getPage() - 1, 3, new Sort(Sort.Direction.DESC, "sales"));
        }else if("RECENT".equals(productSearch.getSort().toUpperCase())){
            pageRequest = PageRequest.of(productSearch.getPage() - 1, 3, new Sort(Sort.Direction.DESC, "regtime"));
        }

        return productRepository.getProductsByDSL(productSearch, pageRequest);
    }
}
