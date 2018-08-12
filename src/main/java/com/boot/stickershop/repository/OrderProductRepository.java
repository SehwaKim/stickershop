package com.boot.stickershop.repository;

import com.boot.stickershop.domain.BasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<BasketProduct, Long> {

}
