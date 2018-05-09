//package com.boot.stickershop.domain;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "wishlist_product")
//@Getter
//@Setter
//public class WishlistProduct {
////    public WishlistProduct() { regtime = LocalDateTime.now(); }
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    private Long id;
////    @ManyToOne(targetEntity = Product.class, fetch=FetchType.EAGER)
////    @JoinColumn(name = "product_id")
////    private Product product;
////    private int quantity; // 상품 수량.
////    private LocalDateTime regtime;
////    @ManyToOne
////    @JoinColumn(name = "wishlist_id")
////    private WishlistItem wishlist;
////
////    public void addWishlist(WishlistItem wishlist){
////        this.wishlist = wishlist;
////        if(!wishlist.getWishlistProducts().contains(this)){
////            wishlist.getWishlistProducts().add(this);
////        }
////    }
//}
