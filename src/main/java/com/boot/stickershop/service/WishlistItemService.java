package com.boot.stickershop.service;

import com.boot.stickershop.domain.Product;
import com.boot.stickershop.domain.User;
import com.boot.stickershop.domain.UserConnection;
import com.boot.stickershop.domain.WishlistItem;

import java.security.Principal;
import java.util.List;

public interface WishlistItemService {
    public WishlistItem addWishlist(WishlistItem item);
    public void deleteWishlist(Long id);
    public WishlistItem selectOne(Long id);
    public List<WishlistItem> selectAll();
    public WishlistItem selectOneByProduct(Product product);
}
