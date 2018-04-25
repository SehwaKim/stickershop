package com.boot.stickershop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/*
 카테고리 분할 방식 검토 필요////
 */
@Entity
@Table(name = "board_categories")
@Getter
@Setter
public class BoardCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;  // q&a, FAQ ...
}
