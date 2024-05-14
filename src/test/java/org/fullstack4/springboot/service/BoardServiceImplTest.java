package org.fullstack4.springboot.service;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.springboot.dto.BoardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@SpringBootTest
public class BoardServiceImplTest {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegist() {
        log.info("==========================");
        log.info("BoardServiceImplTest >> testRegist START");

        log.info("boardService.getClass().getName() : {} ", boardService.getClass().getName());

        BoardDTO boardDTO = BoardDTO.builder()
                .user_id("test")
                .title("제목 테스트")
                .content("내용 테스트")
                .display_date("2024-05-13")
                .build();

        int result = boardService.regist(boardDTO);

        log.info("boardDTO : {}", boardDTO);
        log.info("result : {}", result);
        log.info("BoardServiceImplTest >> testRegist END");
        log.info("==========================");
    }

    @Test
    public void testView() {
        log.info("==========================");
        log.info("BoardServiceImplTest >> testView START");
        BoardDTO boardDTO = boardService.view(28);

        log.info("boardDTO : {}", boardDTO);
        log.info("BoardServiceImplTest >> testView END");
        log.info("==========================");
    }

    @Test
    public void testModify() {
        log.info("==========================");
        log.info("BoardServiceImplTest >> testModify START");

        BoardDTO boardDTO = BoardDTO.builder()
                .idx(28)
                .user_id("test")
                .title("제목 수정 테스트 28")
                .content("내용 수정 테스트 28")
                .display_date("2024-05-13")
                .build();

         boardService.modify(boardDTO);

        log.info("boardDTO : {}", boardDTO);
        log.info("BoardServiceImplTest >> testModify END");
        log.info("==========================");
    }

    @Test
    public void testDelete() {
        log.info("==========================");
        log.info("BoardServiceImplTest >> testDelete START");

        boardService.delete(28);

        log.info("BoardServiceImplTest >> testDelete END");
        log.info("==========================");
    }
}