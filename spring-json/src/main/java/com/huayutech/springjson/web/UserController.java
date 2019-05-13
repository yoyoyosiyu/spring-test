package com.huayutech.springjson.web;

import com.huayutech.springjson.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("/users")
public class UserController {


    @PersistenceContext
    EntityManager entityManager;


    @GetMapping("/{userId}")
    public User doGetUser(@PathVariable Long userId) {

        User user = entityManager.find(User.class, userId);

        return user;

    }
}
