package com.huayutech.springjpamultiselect.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String content;

    @ManyToOne(fetch = FetchType.LAZY)
    Article article;
}
