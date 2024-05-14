package org.fullstack4.springboot.repository;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.springboot.domain.BoardEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@SpringBootTest
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testGetNow() {
        String now = boardRepository.getNow();
        log.info("=======================");
        log.info("now : " + now);
        log.info("=======================");
    }

    @Test
    public void testInsert() {
        log.info("==============================");
        log.info("Board Insert Test Start");

        IntStream.rangeClosed(0, 10)
                .forEach(i -> {
                    BoardEntity bbs = BoardEntity.builder()
                            .user_id("test")
                            .title("테스트 제목 " + i)
                            .content("테스트 내용 " + i)
                            .display_date(new SimpleDateFormat("yyyy-MM-dd").format(new Date(2024-1900, 3, (i%10==0 ? 1 : i%10))).toString())
                            .build();

                    BoardEntity result = boardRepository.save(bbs);
                    log.info("result : " + result);
                });

        log.info("Board Insert Test END");
        log.info("==============================");
    }

    @Test
    public void testView() {
        log.info("==============================");
        log.info("Board View Test Start");

        int idx = 12;

        Optional<BoardEntity> result = boardRepository.findById(idx);
        BoardEntity bbs = result.orElse(null); // 없으면 null로 return 한다.

        // result.get(); // 값이 없으면 NoSuchElementException 발생
        // -> 다양한 예외처리 방법
//        if(result.isPresent()) {
//        } else {
//            throw new IllegalArgumentException();
//        }
//        result.orElseThrow(IllegalArgumentException::new);
//        result.orElseThrow(() -> new IllegalArgumentException("No Find Data"));
//        result.orElseGet(BoardEntity::new);
//        result.ifPresent(b -> {log.info("result:{}", b);});

        log.info("bbs : {} " + bbs);
        log.info("Board View Test END");
        log.info("==============================");
    }

    @Test
    public void testModify() {
        log.info("==============================");
        log.info("Board Modify Test Start");

        int idx = 2;

        Optional<BoardEntity> result = boardRepository.findById(idx);
        BoardEntity bbs = result.orElse(
                BoardEntity.builder()
                        .user_id("test")
                        .title("제목 수정 1")
                        .content("내용 수정 1")
                        .display_date(
                                new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString()
                        )
                        .build()
        );

        bbs = BoardEntity.builder()
                        .idx(idx)
                        .user_id("test")
                        .title("제목 수정 2")
                        .content("내용 수정 2")
                        .display_date(
                                new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString()
                        )
                        .build();

        bbs.modify("test", "제목 수정 1", "내용 수정 1", new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString());

        boardRepository.save(bbs);

        log.info("bbs : {} " + bbs);
        log.info("Board Modify Test End");
        log.info("==============================");
    }

    @Test
    public void testDelete() {
        int idx = 1;

        boardRepository.deleteById(idx);
    }

    @Test
    public void testList() {
        log.info("==============================");
        log.info("Board List Test Start");

        PageRequest pageable = PageRequest.of(0, 10, Sort.by("idx").descending());

//        Page<BoardEntity> page = boardRepository.search(pageable);
        Page<BoardEntity> result = boardRepository.search(pageable);

//        log.info("page :" + page);
        log.info("result {} :" + result);
        log.info("Board List Test End");
        log.info("==============================");
    }

    @Test
    public void testSearchList() {
        log.info("==============================");
        log.info("Board Searched List Test Start");

        PageRequest pageable = PageRequest.of(0, 10, Sort.by("idx").descending());

        String[] types = {"t", "c", "w"};
        String search_word = "test";

        Page<BoardEntity> page = boardRepository.search2(pageable, types, search_word);

        log.info("page :" + page);
        log.info("Board Searched List Test End");
        log.info("==============================");
    }
}