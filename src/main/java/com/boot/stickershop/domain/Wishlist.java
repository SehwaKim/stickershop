package com.boot.stickershop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wishlists")
@Getter
@Setter
public class Wishlist {
    public Wishlist() { regtime = LocalDateTime.now(); }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime regtime;
    @ManyToOne(targetEntity = User.class,fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;
    @OneToMany(mappedBy = "wishlist", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<WishlistProduct> wishlistProducts = new ArrayList<>();

    public void addWishlistProduct(WishlistProduct wishlistProduct){
        wishlistProducts.add(wishlistProduct);
        if(wishlistProduct.getWishlist() != this){
            wishlistProduct.setWishlist(this);
        }
    }
}
