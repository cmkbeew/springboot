package org.fullstack4.springboot.service;

import org.fullstack4.springboot.dto.BoardReplyDTO;
import org.fullstack4.springboot.dto.PageRequestDTO;
import org.fullstack4.springboot.dto.PageResponseDTO;

public interface BoardReplyService {

    int regist(BoardReplyDTO replyDTO);

    PageResponseDTO<BoardReplyDTO> getListOfReply(int bbs_idx, PageRequestDTO pageRequestDTO);
}
