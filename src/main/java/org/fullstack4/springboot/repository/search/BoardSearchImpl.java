package org.fullstack4.springboot.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.springboot.domain.BoardEntity;
import org.fullstack4.springboot.domain.QBoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() {
        super(BoardEntity.class);
    }

    @Override
    public Page<BoardEntity> search(Pageable pageable) {
        log.info("===================================");
        log.info("BoardSearchImpl >> search Start");

        QBoardEntity qBoard = QBoardEntity.boardEntity; // QBoardEntity 객체 생성

        JPQLQuery<BoardEntity> query = from(qBoard);    // SELECT ... FROM QBoardEntity --> tbl_board

        // paging
        this.getQuerydsl().applyPagination(pageable, query);

        log.info("query : {} " + query);

        // where가 2개 이상이면 AND로 묶임
//        query.where(qBoard.title.contains("수정"));

        // OR 사용 방법
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.or(qBoard.title.contains("제목"));
        booleanBuilder.or(qBoard.content.contains("제목"));

        query.where(booleanBuilder);

        List<BoardEntity> boards = query.fetch();
        int total = (int)query.fetchCount();

        log.info("boards : {} " + boards);
        log.info("total : {} " + total);

        log.info("BoardSearchImpl >> search End");
        log.info("===================================");

        return new PageImpl<>(boards, pageable, total);
    }

    @Override
    public Page<BoardEntity> search2(Pageable pageable, String[] types, String search_word) {
        log.info("===================================");
        log.info("BoardSearchImpl >> search2 Start");

        QBoardEntity qBoard = QBoardEntity.boardEntity;
        JPQLQuery<BoardEntity> query = from(qBoard);

        if((types != null && types.length > 0) && (search_word != null && search_word.length() > 0)) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            // type: t-제목, c-내용, w-사용자아이디
            for(String type : types) {
                switch (type) {
                    case "t" :
                        booleanBuilder.or(qBoard.title.contains(search_word)); break;
                    case "c" :
                        booleanBuilder.or(qBoard.content.contains(search_word)); break;
                    case "w" :
                        booleanBuilder.or(qBoard.user_id.contains(search_word)); break;
                }
            }

            query.where(booleanBuilder);
        }

        query.where(qBoard.idx.gt(0));

        // paging
        this.getQuerydsl().applyPagination(pageable, query);

        log.info("query : {} " + query);

        List<BoardEntity> boards = query.fetch();
        int total = (int)query.fetchCount();

        log.info("boards : {} " + boards);
        log.info("total : {} " + total);


        log.info("BoardSearchImpl >> search2 End");
        log.info("===================================");

        return new PageImpl<>(boards, pageable, total);
    }
}
