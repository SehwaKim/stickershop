package com.boot.stickershop.dto;

import com.boot.stickershop.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class OrderSearch {
    /*수령인 주문번호 로 검색 searchType, searchStr
    기간별 검색 date_to, date_from
    최근순,과거순 sort
    주문상태별 status
    */
    private Long userId;
    private String orderNo;
    private int page;
    private String searchType;
    private String searchStr;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private String sort;
    private Integer status;

    public OrderSearch() {
        page = 1;
        sort = "Recent"; // 최근순
        dateFrom = LocalDateTime.now();
        dateTo = dateFrom.minusMonths(3); // 기본 3개월
    }
}
