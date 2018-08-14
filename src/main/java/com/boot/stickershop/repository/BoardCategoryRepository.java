package com.boot.stickershop.repository;

import com.boot.stickershop.domain.BoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCategoryRepository
        extends JpaRepository<BoardCategory, Long> {
}
