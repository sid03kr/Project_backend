package com.sid03kr.projcet.repository;

import com.sid03kr.projcet.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardApiRepository extends JpaRepository<Board, Long> {
}
