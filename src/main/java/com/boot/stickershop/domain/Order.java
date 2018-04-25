package com.boot.stickershop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    public Order() { regtime = LocalDateTime.now(); }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String payment;  // 결제 방법
    private int quantity; // 상품 수량.
    private LocalDateTime regtime;
    private int status; // 주문 상태 주문=1, 취소=2,

    @ManyToOne(targetEntity = User.class,fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(targetEntity = Product.class,fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    // 헬퍼
    void addDelivery(Delivery delivery){
        this.delivery = delivery;
        if(!delivery.getOrders().contains(this)){
            delivery.getOrders().add(this);
        }
    }

}
