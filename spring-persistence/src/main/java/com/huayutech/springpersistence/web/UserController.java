package com.huayutech.springpersistence.web;


import com.huayutech.springpersistence.domain.collection.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("/users")
public class UserController {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @PostMapping
    public User doCreateUser(@RequestBody User user) {

        String name = new String("hello");

        int hashcode = name.hashCode();


        name = new String("hello");

        int hashcode1 = name.hashCode();


        entityManager.persist(user);

        return user;
    }


    @Transactional
    @PostMapping("/{userId}")
    public User doUpdateUser(@PathVariable Long userId, @RequestBody User user) {

        User pUser = entityManager.find(User.class, userId);

        if (user == null) {
            throw new EntityNotFoundException(String.format("#%d的用户不存在", userId));
        }

        user.setId(pUser.getId());

        entityManager.merge(user);

        //user.getAttributes().clear();

        //user.getAttributes().put("age", "16");

        //entityManager.persist(user);

        return user;

    }

    @GetMapping("/{userId}")
    public User doGetUser(@PathVariable Long userId) {
        User user = entityManager.find(User.class, userId);

        return user;
    }

}
