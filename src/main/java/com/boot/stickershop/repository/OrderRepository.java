package com.boot.stickershop.repository;

import com.boot.stickershop.base.JpaQueryDslPredicateRepository;
import com.boot.stickershop.domain.Order;

public interface OrderRepository extends JpaQueryDslPredicateRepository<Order, Long> {
}
