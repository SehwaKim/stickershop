package com.boot.stickershop.repository;

import com.boot.stickershop.domain.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {
    public List<WishlistItem> findWishlistItemsById(Long id);
    public WishlistItem findWishlistItemById(Long id);

    @Query("SELECT w FROM WishlistItem w WHERE w.product.id = ?1 and w.user.id = ?2")
    public WishlistItem findByProductIdAndUserId(Long productId, Long userId);

    @Query("SELECT w FROM WishlistItem w WHERE w.user.id = ?1")
    public List<WishlistItem> findAllByUserId(Long UserId);
}
