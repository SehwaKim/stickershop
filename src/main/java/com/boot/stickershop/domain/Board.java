package com.boot.stickershop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "boards")
@Getter
@Setter
public class Board {
    public Board(){
        regtime = LocalDateTime.now();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime regtime;
    private LocalDateTime edittime;  // 수정날짜
//    private int categoryNo;  // 공지=1, FAQ=2, Q&A=3
    private String title;
    private String content;
    private String writer;
    private int hit; // 조회수

}
