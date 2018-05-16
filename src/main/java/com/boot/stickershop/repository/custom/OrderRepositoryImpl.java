package com.boot.stickershop.repository.custom;

import com.boot.stickershop.domain.Order;
import com.boot.stickershop.domain.QOrder;
import com.boot.stickershop.dto.OrderSearch;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;

import javax.persistence.EntityManager;

public class OrderRepositoryImpl implements OrderRepositoryCustom {
    @Autowired
    EntityManager entityManager;

    @Override
    public Page<Order> getOrdersByDSL(OrderSearch orderSearch, Pageable pageable) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        QOrder qOrder = QOrder.order;
        JPAQuery<Order> jpaQuery = jpaQueryFactory.selectFrom(qOrder);

        // nullable - orderNo, searchStr, status

        if(orderSearch.getOrderNo() != null){ // 주문번호로만 찾기(orderNo)
            jpaQuery.where(qOrder.orderNo.eq(orderSearch.getOrderNo()));

            return new PageImpl<Order>(jpaQuery.fetch(), pageable, jpaQuery.fetchCount());
        }

        jpaQuery.where(qOrder.user.id.eq(orderSearch.getUserId()));
        jpaQuery.where(qOrder.regtime.between(orderSearch.getDateFrom(), orderSearch.getDateTo()));

        if(orderSearch.getSearchStr() != null){
            if("수령자".equals(orderSearch.getSearchType())){
                jpaQuery.where(qOrder.receiver.contains(orderSearch.getSearchStr()));
            }else if("주문번호".equals(orderSearch.getSearchType())){
                //jpaQuery.where()
            }
        }

        if(orderSearch.getStatus() != null){
            jpaQuery.where(qOrder.status.eq(orderSearch.getStatus()));
        }

        PathBuilder<Order> pathBuilder = new PathBuilder<Order>(Order.class, "order");
        Querydsl querydsl = new Querydsl(entityManager, pathBuilder);
        JPQLQuery<Order> query = querydsl.applyPagination(pageable, jpaQuery);
        Long total = jpaQuery.fetchCount();

        return new PageImpl<Order>(query.fetch(), pageable, total);
    }
}