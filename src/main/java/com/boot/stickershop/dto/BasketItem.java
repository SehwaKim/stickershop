package com.boot.stickershop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BasketItem {
    private Long productId;
    private int quantity;
}
