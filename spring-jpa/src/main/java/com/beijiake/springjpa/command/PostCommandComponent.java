package com.beijiake.springjpa.command;

import com.beijiake.springjpa.domain.Unidirectional.Post;
import com.beijiake.springjpa.domain.Unidirectional.PostComment;
import com.beijiake.springjpa.utils.RandomNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@ShellComponent("post")
public class PostCommandComponent {


    @Autowired
    EntityManager entityManager;

    @Transactional
    @ShellMethod("create a post")
    public String createPost() {

        Post post = new Post();


        post.setId(RandomNumber.random());
        post.setTitle("hello, world");

        PostComment postComment = new PostComment();
        postComment.setReview("This is a good post!");
        post.getComments().add(postComment);

        entityManager.persist(post);

        return "DONE";

    }

    @Transactional
    @ShellMethod("delete a post")
    public String deletePost(Long id) {

        Post post = entityManager.find(Post.class, id);

        entityManager.remove(post);

        return "DONE";
    }
}