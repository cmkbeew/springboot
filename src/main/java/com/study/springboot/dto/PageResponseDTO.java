package com.study.springboot.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {
    
    private int page;
    private int size;
    private int total;

    private int total_page;
    
    private int start; // 시작 페이지 번호
    private int end; // 끝 페이지 번호
    private boolean prev; // 이전 페이지 존재 여부
    private boolean next; // 다음 페이지 존재 여부
    
    private List<E> dtoList; // 데이터 리스트
    
    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {
        if(total <= 0) {
            return;
        }
        
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();
        
        this.total = total;

        this.total_page = (this.total > 0) ? (int) Math.ceil(this.total / (double) this.size) : 1;

        this.dtoList = dtoList;

        // 화면에서 시작 번호
        this.start = (int) (Math.ceil(this.page / (double) this.size) - 1) * this.size + 1;
        
        // 화면에서 마지막 번호
        this.setEnd();

        this.prev = this.start > 1;
        this.next = total_page > this.end;
    }

    public void setEnd() {
        this.end = (int)Math.ceil(this.page / (double)this.size) * this.size;
        this.end = this.end > this.total_page ? this.total_page : this.end;
    }
}
