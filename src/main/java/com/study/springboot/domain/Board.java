package com.study.springboot.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long bno;

    @Column(length = 500, nullable = false) // 컬럼의 길이 지정과 null 허용여부
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String writer;

    // update를 위한 메서드
    public void change(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
