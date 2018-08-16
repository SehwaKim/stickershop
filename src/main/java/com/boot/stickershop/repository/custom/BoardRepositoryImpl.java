package com.boot.stickershop.repository.custom;

import com.boot.stickershop.domain.Board;
import com.boot.stickershop.domain.QBoard;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class BoardRepositoryImpl extends QuerydslRepositorySupport
        implements BoardRepositoryCustom{
    public BoardRepositoryImpl(){
        super(Board.class); // 부모클래스에게 Entity클래스를 알려준다.
    }

    @Override
    public Board getBoardByDsl(Long id) {
        QBoard qboard = QBoard.board; // gradle이나 maven의 querydsl 플러그그인을 실행.

        JPQLQuery<Board> jpqlQuery = from(qboard); // SELECT b FROM Board b

        return jpqlQuery.where(qboard.id.eq(id)).fetchOne(); // SELECT b FROM Board b WHERE id = :id
    }
}
