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
        hit = 0;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = BoardCategory.class, fetch=FetchType.EAGER)
    @JoinColumn(name="catetory_id")
    private BoardCategory boardCategory;
    private String title;
    private String content;
    @ManyToOne(targetEntity = User.class, fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    private int hit;
    private LocalDateTime regtime;
    private LocalDateTime edittime;

    @OneToMany(mappedBy = "board")
    private List<BoardFile> boardFiles = new ArrayList<>();

    public void addBoardFiles(BoardFile boardFile){
        boardFiles.add(boardFile);
        if(boardFile.getBoard() != this){
            boardFile.setBoard(this);
        }
    }
}
