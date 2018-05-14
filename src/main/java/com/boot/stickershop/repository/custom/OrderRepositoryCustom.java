package com.boot.stickershop.repository.custom;

import com.boot.stickershop.domain.Order;
import com.boot.stickershop.dto.OrderSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderRepositoryCustom {
    Page<Order> getOrdersByDSL(OrderSearch orderSearch, Pageable pageable);
}