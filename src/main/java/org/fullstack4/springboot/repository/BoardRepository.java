package org.fullstack4.springboot.repository;

import org.fullstack4.springboot.domain.BoardEntity;
import org.fullstack4.springboot.repository.search.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer>, BoardSearch {

    @Query(value = "SELECT NOW()", nativeQuery = true)
    public String getNow();
}
