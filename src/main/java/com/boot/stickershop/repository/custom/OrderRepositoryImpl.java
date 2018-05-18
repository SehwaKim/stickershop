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

        /* 주문번호로만 찾기(orderNo) */
        if(orderSearch.getOrderNo() != null && !"".equals(orderSearch.getOrderNo())){
            jpaQuery.where(qOrder.orderNo.eq(orderSearch.getOrderNo()));

            return new PageImpl(jpaQuery.fetch(), pageable, jpaQuery.fetchCount());
        }
        /* 수령인 + 전화번호 찾기 */
        if(orderSearch.getReceiver() != null && !"".equals(orderSearch.getReceiver()) && orderSearch.getPhone1() != null && orderSearch.getPhone2() != null && orderSearch.getPhone3() != null){
            jpaQuery.where(qOrder.receiver.eq(orderSearch.getReceiver()));
            jpaQuery.where(qOrder.phone1.eq(orderSearch.getPhone1()));
            jpaQuery.where(qOrder.phone2.eq(orderSearch.getPhone2()));
            jpaQuery.where(qOrder.phone3.eq(orderSearch.getPhone3()));

            return new PageImpl(jpaQuery.fetch(), pageable, jpaQuery.fetchCount());
        }

        if(orderSearch.getUserId() == null){
            return null;
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
        jpaQuery.orderBy(qOrder.regtime.desc()); // TODO 오래된순으로 정렬도 추가하기
        System.out.println(orderSearch);
        return new PageImpl(query.fetch(), pageable, total);
    }
}