package com.boot.stickershop.service.impl;

import com.boot.stickershop.domain.Product;
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
    public Page<Product> getProductList(Long categoryId, int page, String sort, String keyword, Integer minPrice, Integer maxPrice) {
        Page<Product> list = null;
        PageRequest pageRequest = null;

        // nullable : categoryId, keyword, minPrice, maxPrice
        // 공통 적용 page, sort
        // 특정 카테고리 categoryId
        // 검색어 keyword
        // 가격대 minPrice, maxPrice

        if(sort != null){
            sort = sort.toUpperCase();
        }

        if("FAVOR".equals(sort)){
            pageRequest = PageRequest.of(page - 1, 21, new Sort(Sort.Direction.DESC, "sales"));
        }else if("RECENT".equals(sort)){
            pageRequest = PageRequest.of(page - 1, 21, new Sort(Sort.Direction.DESC, "regtime"));
        }

        // 카테고리 유무 o

        // 기본

        // 검색어만
        // 검색어 + 가격

        // 가격만

        if(categoryId != null){ // 카테고리 o
            if(keyword != null){ // 검색어만 , 검색어+가격
                if(minPrice == null && maxPrice == null){ // 검색어만

                }else if (minPrice == null){

                }else if (maxPrice == null){

                }else {

                }
            }else { // 기본, 가격만
                if(minPrice == null && maxPrice == null){ // 기본

                }else if (minPrice == null){

                }else if (maxPrice == null){

                }else {

                }
            }
        }else {

        }

        return list;
    }
}
