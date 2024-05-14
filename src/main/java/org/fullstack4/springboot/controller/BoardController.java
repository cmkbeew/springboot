package org.fullstack4.springboot.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.springboot.dto.BoardDTO;
import org.fullstack4.springboot.dto.PageRequestDTO;
import org.fullstack4.springboot.dto.PageResponseDTO;
import org.fullstack4.springboot.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<BoardDTO> pageResponseDTO = boardService.list(pageRequestDTO);

        log.info("pageResponseDTO : {}", pageResponseDTO);
        model.addAttribute("pageResponseDTO", pageResponseDTO);
    }

    @GetMapping("/view")
    public void view(int idx, PageRequestDTO pageRequestDTO, Model model) {
        log.info("==================================");
        log.info("BoardController view START");

        BoardDTO boardDTO = boardService.view(idx);
        model.addAttribute("dto", boardDTO);

        log.info("BoardController view ENd");
        log.info("==================================");
    }

    @GetMapping("/regist")
    public void registGet(Model model) {

    }

    @PostMapping("/regist")
    public String registPost(@Valid BoardDTO boardDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("dto", boardDTO);

            return "redirect:/board/regist";
        }

        int result_idx = boardService.regist(boardDTO);


        return "redirect:/board/list";
    }
}
