package com.boot.stickershop.repository.custom;

import com.boot.stickershop.domain.Order;
import com.boot.stickershop.domain.QOrder;
import com.boot.stickershop.dto.OrderSearch;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// QuerydslRepositorySupport를 상속받으면 EntityManager를 사용할 필요가 없다.
public class OrderRepositoryImpl extends QuerydslRepositorySupport implements OrderRepositoryCustom {

    // Entity 클래스를 부모 클래스의 생성자로 전달해야 한다.
    public OrderRepositoryImpl(){
        super(Order.class);
    }

    @Override
    public Page<Order> getOrdersByDSL(OrderSearch orderSearch, Pageable pageable) {

        QOrder qOrder = QOrder.order;
        JPQLQuery<Order> jpqlQuery = from(qOrder);

        // nullable - orderNo, searchStr, status

        /* 주문번호로만 찾기(orderNo) */
        if(orderSearch.getOrderNo() != null && !"".equals(orderSearch.getOrderNo())){
            jpqlQuery.where(qOrder.orderNo.eq(orderSearch.getOrderNo().trim()));
            jpqlQuery.where(qOrder.user.isNull());

            return new PageImpl(jpqlQuery.fetch(), pageable, jpqlQuery.fetchCount());
        }
        /* 수령인 + 전화번호 찾기 */
        if(orderSearch.getReceiver() != null && !"".equals(orderSearch.getReceiver()) && orderSearch.getPhone1() != null && orderSearch.getPhone2() != null && orderSearch.getPhone3() != null){
            jpqlQuery.where(qOrder.receiver.eq(orderSearch.getReceiver().trim()));
            jpqlQuery.where(qOrder.phone1.eq(orderSearch.getPhone1()));
            jpqlQuery.where(qOrder.phone2.eq(orderSearch.getPhone2()));
            jpqlQuery.where(qOrder.phone3.eq(orderSearch.getPhone3()));
            jpqlQuery.where(qOrder.user.isNull());

            return new PageImpl(jpqlQuery.fetch(), pageable, jpqlQuery.fetchCount());
        }

        if(orderSearch.getUserId() == null){
            return null;
        }

        jpqlQuery.where(qOrder.user.id.eq(orderSearch.getUserId()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime from = LocalDateTime.from(LocalDate.parse(orderSearch.getDateFrom(), formatter).atStartOfDay());
        LocalDateTime to = LocalDateTime.from(LocalDate.parse(orderSearch.getDateTo(), formatter).atStartOfDay()).plusDays(1);
        jpqlQuery.where(qOrder.regtime.between(from, to));

        if(orderSearch.getSearchStr() != null){
            if("수령자".equals(orderSearch.getSearchType())){
                jpqlQuery.where(qOrder.receiver.contains(orderSearch.getSearchStr()));
            }else if("주문번호".equals(orderSearch.getSearchType())){
                //jpaQuery.where()
            }
        }

        if(orderSearch.getStatus() != null){
            jpqlQuery.where(qOrder.status.eq(orderSearch.getStatus()));
        }

        JPQLQuery<Order> query = getQuerydsl().applyPagination(pageable, jpqlQuery);
        Long total = jpqlQuery.fetchCount();

        jpqlQuery.orderBy(qOrder.id.desc()); // TODO 오래된순으로 정렬도 추가하기

        return new PageImpl(query.fetch(), pageable, total);
    }
}