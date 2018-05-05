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
    private int quantity; // 상품 수량.
    private LocalDateTime regtime;
    @ManyToOne
    @JoinColumn(name = "basket_id")
    private Basket basket;

    public void addBasket(Basket basket){
        this.basket = basket;
        if(!basket.getBasketProducts().contains(this)){
            basket.getBasketProducts().add(this);
        }
    }
}
