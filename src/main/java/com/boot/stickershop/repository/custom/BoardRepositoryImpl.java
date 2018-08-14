package com.boot.stickershop.repository.custom;

import com.boot.stickershop.domain.Board;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class BoardRepositoryImpl extends QuerydslRepositorySupport implements BoardRepositoryCustom{
    public BoardRepositoryImpl(){
        super(Board.class); // 부모클래스에게 Entity클래스를 알려준다.
    }

}
