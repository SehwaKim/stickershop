package com.boot.stickershop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "deliveries")
@Getter
@Setter
public class Delivery {
    public Delivery() { regtime = LocalDateTime.now(); }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime regtime;
    private String receiver;  //  받는사람
    private String zipcode;
    private String addr1;
    private String addr2;
    private String phone1; // 010
    private String phone2;  // xxxx
    private String phone3;
    private String message;
    private int status; // 배송상태정보

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();

    // 헬퍼
    public void addOrder(Order order){
        this.orders.add(order);
        if(order.getDelivery()!=this){
            order.setDelivery(this);
        }
    }
}
