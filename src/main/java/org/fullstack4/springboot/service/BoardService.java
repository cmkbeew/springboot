package org.fullstack4.springboot.service;

import org.fullstack4.springboot.dto.BoardDTO;
import org.fullstack4.springboot.dto.PageRequestDTO;
import org.fullstack4.springboot.dto.PageResponseDTO;

public interface BoardService {

    int regist(BoardDTO boardDTO);

    BoardDTO view(int idx);

    void modify(BoardDTO boardDTO);

    void delete(int idx);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
