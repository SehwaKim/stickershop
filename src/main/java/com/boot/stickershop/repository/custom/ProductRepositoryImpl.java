package com.boot.stickershop.repository.custom;

import com.boot.stickershop.domain.Product;
import com.boot.stickershop.domain.QProduct;
import com.boot.stickershop.dto.ProductSearch;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductRepositoryImpl extends QuerydslRepositorySupport implements ProductRepositoryCustom {
    public ProductRepositoryImpl(){
        super(Product.class);
    }

    @Override
    public Page<Product> getProductsByDSL(ProductSearch productSearch, Pageable pageable) {

        QProduct qProduct = QProduct.product;
        JPQLQuery<Product> jpqlQuery = from(qProduct).leftJoin(qProduct.productFiles).fetchJoin().innerJoin(qProduct.productCategory).fetchJoin();

        // nullable : categoryId, keyword, minPrice, maxPrice

        if(productSearch.getCategoryId() != null){
            jpqlQuery.where(qProduct.productCategory.id.eq(productSearch.getCategoryId()));
        }

        if(productSearch.getKeyword() != null){
            jpqlQuery.where(qProduct.name.containsIgnoreCase(productSearch.getKeyword()));
        }

        if(productSearch.getMinPrice() != null || productSearch.getMaxPrice() != null){
            jpqlQuery.where(qProduct.price.between(productSearch.getMinPrice(), productSearch.getMaxPrice()));
        }

        JPQLQuery query = getQuerydsl().applyPagination(pageable, jpqlQuery);
        query.orderBy(qProduct.id.asc());
        Long total = jpqlQuery.fetchCount();

        return new PageImpl(query.fetch(), pageable, total);
    }

    @Override
    public List<Product> getMainProductsByDSL() {
        QProduct qProduct = QProduct.product;
        JPQLQuery<Product> jpqlQuery = from(qProduct).leftJoin(qProduct.productFiles).fetchJoin().innerJoin(qProduct.productCategory).fetchJoin();

        jpqlQuery.orderBy(qProduct.sales.desc()).orderBy(qProduct.id.asc()).limit(15);

        return jpqlQuery.fetch();
    }
}
