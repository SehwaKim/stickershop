package com.boot.stickershop.repository.custom;

import com.boot.stickershop.domain.Board;

public interface BoardRepositoryCustom {
    // 다이나믹 jpql이 사용될 메소드를 선언
    public Board getBoardByDsl(Long id);
}
