package com.huayu.querydsl.rest;

import com.google.common.collect.Lists;
import com.huayu.querydsl.domain.Post;
import com.huayu.querydsl.domain.QPost;
import com.huayu.querydsl.protocol.ResponseEnvelope;
import com.huayu.querydsl.repository.PostRepository;
import com.huayu.querydsl.utils.RandomNumber;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity doGetPosts(@RequestParam String author) {

        QPost qpost = QPost.post;

        Predicate predicate = qpost.author.eq(author);

        Iterable<Post> iterable = postRepository.findAll(predicate);

        List<Post> posts = Lists.newArrayList();

        iterable.forEach(posts::add);

        return ResponseEnvelope.build(posts, HttpStatus.OK);

    }


    @GetMapping("/predicate")
    public ResponseEntity doGetPostsByPredicate(Predicate predicate) {


        Iterable<Post> iterable = postRepository.findAll(predicate);

        List<Post> posts = Lists.newArrayList();

        iterable.forEach(posts::add);

        return ResponseEnvelope.build(posts, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public void doDeletePost(@PathVariable Long postId) {

        postRepository.deleteById(postId);

    }

    @PostMapping
    public Post doCreatePost(@RequestParam String author, @RequestParam String content) {

        Post post = new Post();

        post.setId(RandomNumber.random());
        post.setAuthor(author);
        post.setContent(content);

        postRepository.save(post);

        return post;

    }


    @PutMapping("/{postId}")
    public void doUpdatePost(@PathVariable Long postId) {
        return;
    }

}
