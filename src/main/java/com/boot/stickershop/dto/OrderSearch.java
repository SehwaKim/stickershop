package com.boot.stickershop.dto;

import com.boot.stickershop.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private String receiver;
    private String phone1;
    private String phone2;
    private String phone3;
    private int page;
    private String searchType;
    private String searchStr;
    private String dateFrom;
    private String dateTo;
    private String sort;
    private Integer status;

    public OrderSearch() {
        page = 1;
        sort = "Recent"; // 최근순
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dateTo = LocalDateTime.now().format(formatter).toString();
        dateFrom = LocalDateTime.now().minusMonths(3).format(formatter).toString(); // 기본 3개월
    }
}
