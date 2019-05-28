package com.huayutech.springjpamultiselect.repository;

import com.huayutech.springjpamultiselect.DTO.ArticleDTO;
import com.huayutech.springjpamultiselect.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("select new com.huayutech.springjpamultiselect.DTO.ArticleDTO(a.content, c.iterator) from Article a left join Comment c on c.article = a")
    List<ArticleDTO> getArticles();

}
