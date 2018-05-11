package com.boot.stickershop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "basket_products")
@Getter
@Setter
public class BasketProduct {
    public BasketProduct() { regtime = LocalDateTime.now(); }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = Product.class, fetch=FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;
    private LocalDateTime regtime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void addUser(User user){
        this.user = user;
        if(!user.getBasketProducts().contains(this)){
            user.getBasketProducts().add(this);
        }
    }
}
