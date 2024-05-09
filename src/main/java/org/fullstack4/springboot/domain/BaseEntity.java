package org.fullstack4.springboot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// 공통으로 사용되는 컬럼을 지정
// 해당 클래스를 상속하여 사용
// 추상 클래스로 지정, @EntityListeners(value={AuditingEntityListener.class}) 적용 -> 가장 중요! -> Spring JPA의 AuditingEntityListener 사용
// -> 엔티티가 DB에 추가, 변경될 때 자동으로 시간 값 지정
// -> 스프링부트 어플리케이션 클래스에 @EnableJpaAuditing 추가 -> AuditingEntityListener 활성화를 위해 필요
@MappedSuperclass 
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
public abstract class BaseEntity {

    @CreatedDate
    @Column(name="reg_date", updatable = false)
    private LocalDateTime reg_date;

    @LastModifiedDate
    @Column(name="modify_date", nullable = true, insertable = false, updatable = true)
    private LocalDateTime modify_date;

    public void setModify_date(LocalDateTime modify_date) {
        this.modify_date = modify_date;
    }
}
