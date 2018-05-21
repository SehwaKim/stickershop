package com.boot.stickershop.domain;

import com.boot.stickershop.util.OrderCode;
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
    public Order() {
        status = OrderCode.STATUS_ORDERED;
        payment = OrderCode.PAYMENT_CASH;
        regtime = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer payment;
    @Column(name = "payment_str")
    private String paymentStr;
    private LocalDateTime regtime;
    private Integer status;
    @Column(name = "status_str")
    private String statusStr;
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
    private String depositor;
    @Column(name = "order_no")
    private String orderNo;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    // 헬퍼
    public void addOrderProducts(OrderProduct orderProduct){
        this.orderProducts.add(orderProduct);
        if(orderProduct.getOrder()!=this){
            orderProduct.setOrder(this);
        }
    }
}
