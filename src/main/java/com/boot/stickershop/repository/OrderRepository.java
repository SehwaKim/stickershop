package com.boot.stickershop.repository;

import com.boot.stickershop.domain.Order;
import com.boot.stickershop.repository.custom.OrderRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {
    Order findFirstByUserIdOrderByRegtimeDesc(Long userId);
}
