package com.boot.stickershop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    //private int quantity; // 상품 수량.
    private LocalDateTime regtime;
    @Column(name = "status")
    private int status; // 주문 상태 주문=1, 취소=2,
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    private String receiver;  //  받는사람
    private String zipcode;
    private String addr1;
    private String addr2;
    private String phone1; // 010
    private String phone2;  // xxxx
    private String phone3;
    private String message;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    // 헬퍼
    public void addorderProducts(OrderProduct orderProduct){
        this.orderProducts.add(orderProduct);
        if(orderProduct.getOrder()!=this){
            orderProduct.setOrder(this);
        }
    }
}
