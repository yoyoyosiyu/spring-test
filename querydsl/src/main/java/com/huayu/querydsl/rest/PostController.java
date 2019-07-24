package com.huayu.querydsl.rest;

import com.google.common.collect.Lists;
import com.huayu.querydsl.domain.Post;
import com.huayu.querydsl.domain.QPost;
import com.huayu.querydsl.repository.PostRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@CrossOrigin(methods = {RequestMethod.OPTIONS, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
@CrossOrigin
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

    @PostMapping
    public void doCreatePost() {
        return;
    }

}
