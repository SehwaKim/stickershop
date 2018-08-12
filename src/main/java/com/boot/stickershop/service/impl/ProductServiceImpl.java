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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Product> getProductList(ProductSearch productSearch) {
        PageRequest pageRequest = null;
        if(productSearch.isAdmin() != null && productSearch.isAdmin()){
            if("FAVOR".equals(productSearch.getSort().toUpperCase())){
                pageRequest = PageRequest.of(productSearch.getPage() - 1, 20, new Sort(Sort.Direction.DESC, "sales"));
            }else if("RECENT".equals(productSearch.getSort().toUpperCase())){
                pageRequest = PageRequest.of(productSearch.getPage() - 1, 20, new Sort(Sort.Direction.DESC, "regtime"));
            }
        }else{
            if("FAVOR".equals(productSearch.getSort().toUpperCase())){
                pageRequest = PageRequest.of(productSearch.getPage() - 1, 9, new Sort(Sort.Direction.DESC, "sales"));
            }else if("RECENT".equals(productSearch.getSort().toUpperCase())){
                pageRequest = PageRequest.of(productSearch.getPage() - 1, 9, new Sort(Sort.Direction.DESC, "regtime"));
            }
        }

        return productRepository.getProductsByDSL(productSearch, pageRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductListMain() {
        return productRepository.getMainProductsByDSL();
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProduct(Long productId) {
        return productRepository.findProductById(productId);
    }
}
