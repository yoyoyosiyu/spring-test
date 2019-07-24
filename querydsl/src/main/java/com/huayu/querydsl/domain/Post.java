package com.huayu.querydsl.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Post {

    @Id
    Long id;

    @Column
    String author;

    @Column
    String content;

    @Column
    Boolean deleted;

    @CreationTimestamp
    LocalDateTime createAt;

    @LastModifiedDate
    LocalDateTime updateAt;

}
