package com.boot.stickershop.domain;

import com.boot.stickershop.util.OrderCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
public class Order {
    public Order() {
        status = OrderCode.STATUS_ORDERED;
        regtime = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer payment;
    private LocalDateTime regtime;
    @Column(name = "status")
    private Integer status;
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
