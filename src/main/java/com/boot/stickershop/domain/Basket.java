package com.boot.stickershop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "baskets")
@Getter
@Setter
public class Basket {
    public Basket() { regtime = LocalDateTime.now(); }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime regtime;
    @ManyToOne(targetEntity = User.class,fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;
    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BasketProduct> basketProducts = new ArrayList<>();

    public void addBasketProduct(BasketProduct basketProduct){
        basketProducts.add(basketProduct);
        if(basketProduct.getBasket() != this){
            basketProduct.setBasket(this);
        }
    }
}
