package com.huayutech.springpersistence;

import com.huayutech.springpersistence.domain.collection.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@SpringBootApplication
public class SpringPersistenceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringPersistenceApplication.class, args);
    }

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        //entityManager.createNativeQuery("delete from user_images").executeUpdate();
        //entityManager.createNativeQuery("delete from user_attributes").executeUpdate();
        //entityManager.createQuery("delete from User").executeUpdate();

         User user = entityManager.createQuery("select u from User u where u.name = :name", User.class).setParameter("name", "simonzeng").getSingleResult();

         find();
         find1();


    }

    public void find() {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery criteriaQuery = cb.createQuery();

        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.select(criteriaQuery.from(User.class));

        criteriaQuery.where(cb.equal(root.get("name"), cb.parameter(String.class, "name")));

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);

        query.setParameter("name", "simonzeng");

        User user = query.getSingleResult();

    }

    public void find1() {

        User user = entityManager.createQuery("select u from User u left join fetch u.images i where i.width = 200", User.class).getSingleResult();

    }
}
