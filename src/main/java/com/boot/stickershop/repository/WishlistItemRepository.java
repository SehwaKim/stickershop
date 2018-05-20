package com.boot.stickershop.repository;

import com.boot.stickershop.base.JpaQueryDslPredicateRepository;
import com.boot.stickershop.domain.Product;
import com.boot.stickershop.domain.WishlistItem;

import java.util.List;

public interface WishlistItemRepository extends JpaQueryDslPredicateRepository<WishlistItem, Long> {
    public List<WishlistItem> findWishlistItemsById(Long id);
    public WishlistItem findWishlistItemById(Long id);
    public WishlistItem findWishlistItemByProduct(Product product);
}
