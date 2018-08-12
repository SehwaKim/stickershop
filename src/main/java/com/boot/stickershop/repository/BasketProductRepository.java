package com.boot.stickershop.repository;

import com.boot.stickershop.domain.BasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Spring DATA JPA를 이용해 개발한다면 보통 JpaRepository를 상속받은 인터페이스를
// 작성하면 된다.
public interface BasketProductRepository extends JpaRepository<BasketProduct, Long> {
    BasketProduct findBasketProductByUserIdAndProductId(Long userId, Long productId);

    @Query("SELECT b FROM BasketProduct b WHERE b.user.id = :id")
    List<BasketProduct> findAllByUserId(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value="delete from BasketProduct c where c.user.id = ?1")
    public void deleteAllByUserId(Long id);

    @Modifying
    @Transactional
    @Query(value="delete from BasketProduct c where c.product.id = ?1")
    public void deleteByProductId(Long id);
}
