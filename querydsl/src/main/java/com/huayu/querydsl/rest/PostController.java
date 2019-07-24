package com.huayu.querydsl.rest;

import com.google.common.collect.Lists;
import com.huayu.querydsl.domain.Post;
import com.huayu.querydsl.domain.QPost;
import com.huayu.querydsl.repository.PostRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostRepository postRepository;


    @GetMapping
    public List<Post> doGetPosts(@RequestParam String author) {

        QPost qpost = QPost.post;

        Predicate predicate = qpost.author.eq(author);

        Iterable<Post> iterable = postRepository.findAll(predicate);

        List<Post> posts = Lists.newArrayList();

        iterable.forEach(posts::add);

        return posts;

    }


    @GetMapping("/predicate")
    public List<Post> doGetPostsByPredicate(Predicate predicate) {


        Iterable<Post> iterable = postRepository.findAll(predicate);

        List<Post> posts = Lists.newArrayList();

        iterable.forEach(posts::add);

        return posts;

    }

}
