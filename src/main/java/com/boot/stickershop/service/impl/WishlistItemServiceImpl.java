package com.boot.stickershop.service.impl;

import com.boot.stickershop.domain.Product;
import com.boot.stickershop.domain.User;
import com.boot.stickershop.domain.UserConnection;
import com.boot.stickershop.domain.WishlistItem;
import com.boot.stickershop.repository.UserConnectionRepository;
import com.boot.stickershop.repository.UserRepository;
import com.boot.stickershop.repository.WishlistItemRepository;
import com.boot.stickershop.service.UserService;
import com.boot.stickershop.service.WishlistItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WishlistItemServiceImpl implements WishlistItemService {

    @Autowired
    WishlistItemRepository repository;

    @Override
    @Transactional
    public WishlistItem addWishlist(WishlistItem item) {
        WishlistItem wishlistItem = repository.save(item);
        return wishlistItem;
    }

    @Override
    @Transactional
    public void deleteWishlist(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public WishlistItem selectOne(Long id) {
        WishlistItem item = repository.findWishlistItemById(id);
        return item;
    }

    @Override
    @Transactional(readOnly = true)
    public List<WishlistItem> selectAllByUserId(Long userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public WishlistItem selectOneByProductIdAndUserId(Long productId, Long userId) {
        return repository.findByProductIdAndUserId(productId, userId);
    }

}
