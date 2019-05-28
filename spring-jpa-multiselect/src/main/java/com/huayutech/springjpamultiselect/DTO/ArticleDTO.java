package com.huayutech.springjpamultiselect.DTO;

import com.huayutech.springjpamultiselect.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class ArticleDTO {

    String content;

    List<Comment> comments;
}
