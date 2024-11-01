package com.study.springboot.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.study.springboot.domain.Board;
import com.study.springboot.domain.QBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search1(Pageable pageable) {
        QBoard board = QBoard.board; // Q도메인 객체

        JPQLQuery<Board> query = from(board); // select ... from board
//        query.where(board.title.contains("1")); // where title like ...

        BooleanBuilder booleanBuilder = new BooleanBuilder(); // ()
        booleanBuilder.or(board.title.contains("11")); // title like ...
        booleanBuilder.or(board.content.contains("11")); // content like ...

        query.where(booleanBuilder);
        query.where(board.bno.gt(0L)); // bno > 0L

        // paging
        this.getQuerydsl().applyPagination(pageable, query); // limit ?, ?

        List<Board> list = query.fetch(); // 쿼리 실행

        long count = query.fetchCount(); // select count() ...

        return null;
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);

        if( (types != null && types.length > 0) && keyword != null ) { // 검색 조건과 키워드가 있는 경우
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String type : types) {
                switch (type) {
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            }

            query.where(booleanBuilder);
        }

        query.where(board.bno.gt(0L));

        // paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }
}
