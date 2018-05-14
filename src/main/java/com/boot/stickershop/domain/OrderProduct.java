package com.boot.stickershop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_products")
@Getter
@Setter
public class OrderProduct {
    public OrderProduct() { regtime = LocalDateTime.now(); }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = Product.class, fetch=FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;
    private LocalDateTime regtime;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public void addOrder(Order order){
        this.order = order;
        if(!order.getOrderProducts().contains(this)){
            order.getOrderProducts().add(this);
        }
    }
}
