package com.boot.stickershop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "board_files")
@Setter
@Getter
public class BoardFile {
    public BoardFile(){ regtime = LocalDateTime.now(); }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "save_file_name")
    private String saveFileName;
    private String contentType;
    private Long length;
    private LocalDateTime regtime;
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    public void addBoard(Board board){
        if(!board.getBoardFiles().contains(this)){
            board.getBoardFiles().add(this);
        }
        this.board = board;
    }
}
