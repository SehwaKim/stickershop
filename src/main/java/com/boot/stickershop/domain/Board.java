package com.boot.stickershop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(targetEntity = BoardCategory.class,fetch=FetchType.LAZY)
    @JoinColumn(name="catetory_id")
    private int categoryId;  // 공지=1, FAQ=2, Q&A=3
    private String title;
    private String content;

    @ManyToOne(targetEntity = User.class,fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    private int hit; // 조회수

    @OneToMany(mappedBy = "board")
    private List<BoardFile> boardFiles = new ArrayList<>();

}
