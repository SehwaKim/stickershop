package com.boot.stickershop.repository;

import com.boot.stickershop.domain.Board;
import com.boot.stickershop.repository.custom.BoardRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository
        extends JpaRepository<Board, Long>, BoardRepositoryCustom {
    // method query
    public Board findBoardById(Long id);
    public List<Board> findBoardByTitleContaining(String title);
    // @Query 가 붙은 메소드

    @Query("SELECT b FROM Board b WHERE b.id = :id")
    public Board getBoard(@Param("id")Long id);
}
