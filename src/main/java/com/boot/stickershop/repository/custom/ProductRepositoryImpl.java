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

import javax.persistence.EntityManager;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepositoryCustom {
    @Autowired
    EntityManager entityManager;

    @Override
    public Page<Product> getProductsByDSL(ProductSearch productSearch, Pageable pageable) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        QProduct qProduct = QProduct.product;
        JPAQuery<Product> jpaQuery = jpaQueryFactory.selectFrom(qProduct);

        // nullable : categoryId, keyword, minPrice, maxPrice

        if(productSearch.getCategoryId() != null){
            jpaQuery.where(qProduct.productCategory.id.eq(productSearch.getCategoryId()));
        }

        if(productSearch.getKeyword() != null){
            jpaQuery.where(qProduct.name.containsIgnoreCase(productSearch.getKeyword()));
        }

        if(productSearch.getMinPrice() != null || productSearch.getMaxPrice() != null){
            jpaQuery.where(qProduct.price.between(productSearch.getMinPrice(), productSearch.getMaxPrice()));
        }

        PathBuilder<Product> product = new PathBuilder<Product>(Product.class, "product");
        Querydsl querydsl = new Querydsl(entityManager, product);
        JPQLQuery query = querydsl.applyPagination(pageable, jpaQuery);
        Long total = jpaQuery.fetchCount();

        return new PageImpl(query.fetch(), pageable, total);
    }

    @Override
    public List<Product> getMainProductsByDSL() {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        QProduct qProduct = QProduct.product;
        JPAQuery<Product> jpaQuery = jpaQueryFactory.selectFrom(qProduct).orderBy(qProduct.sales.desc()).limit(16);

        return jpaQuery.fetch();
    }
}
