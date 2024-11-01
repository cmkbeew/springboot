package com.study.springboot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass // 공통으로 사용되는 컬럼들을 지정하고 상속을 통해 처리하는 어노테이션
// 엔티티가 db에 추가/변경될 때 자동으로 시간 값을 지정할 수 있다.
// 프로그램 실행하는 곳에 @EnableJpaAuditing을 추가해주어야 한다.
@EntityListeners(value = { AuditingEntityListener.class })
@Getter
abstract class BaseEntity {

    @CreatedDate
    @Column(name = "regDate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "modDate")
    private LocalDateTime modDate;
}
