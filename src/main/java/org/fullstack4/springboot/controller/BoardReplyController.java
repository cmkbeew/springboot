package org.fullstack4.springboot.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.springboot.dto.BoardReplyDTO;
import org.fullstack4.springboot.dto.PageRequestDTO;
import org.fullstack4.springboot.dto.PageResponseDTO;
import org.fullstack4.springboot.service.BoardReplyService;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/board/replies")
public class BoardReplyController {

    private final BoardReplyService boardReplyService;

    @Tag(name="REGIST", description = "POST 방식 댓글 등록")
    @PostMapping(value = "/regist", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Integer> regist(@Valid @RequestBody BoardReplyDTO replyDTO,
                                                       BindingResult bindingResult) throws BindException {
        if(bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        Map<String, Integer> map = new HashMap<>();
        int idx = boardReplyService.regist(replyDTO);

        map.put("idx", idx);

        log.info("replyDTO : {}", replyDTO);

        return map;
    }

    @Tag(name="LIST", description = "댓글 목록")
    @GetMapping("/list/{bbs_idx}")
    public PageResponseDTO<BoardReplyDTO> replyList(@PathVariable(name = "bbs_idx") int bbs_idx, PageRequestDTO pageRequestDTO) {
        PageResponseDTO<BoardReplyDTO> responseDTO = boardReplyService.getListOfReply(bbs_idx, pageRequestDTO);

        return responseDTO;
    }
}
