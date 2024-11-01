package com.study.springboot.repository;

import com.study.springboot.domain.Board;
import com.study.springboot.repository.search.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
}
