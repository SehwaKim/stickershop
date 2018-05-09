package com.boot.stickershop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductSearch {
    private Long categoryId;
    private int page;
    private String sort;
    private String keyword;
    private Integer minPrice;
    private Integer maxPrice;

    public ProductSearch() {
        this.page = 1;
        this.sort = "favor";
    }
}
