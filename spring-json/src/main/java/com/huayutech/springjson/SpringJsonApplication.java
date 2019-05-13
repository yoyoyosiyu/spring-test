package com.huayutech.springjson;

import com.huayutech.springjson.domain.Address;
import com.huayutech.springjson.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootApplication
public class SpringJsonApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringJsonApplication.class, args);
    }

    @Autowired
    EntityManager entityManager;

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        entityManager.createQuery("Delete From Address ").executeUpdate();
        entityManager.createQuery("Delete From User").executeUpdate();
        entityManager.flush();




        User user = new User();

        user.setId(12345678L);
        user.setName("yoyoyosiyu");

        Address address = new Address();

        address.setId(1234567L);
        address.setCountry("china");
        address.setProvince("HaiNan");
        address.setCity("HaiKou");
        address.setStreet("SanXi Road");
        address.setUser(user);

        user.getAddresses().add(address);

        address = new Address();

        address.setId(1234568L);
        address.setCountry("china");
        address.setProvince("HaiNan");
        address.setCity("SanYa");
        address.setStreet("XXX477 Road");
        address.setUser(user);

        user.getAddresses().add(address);

        entityManager.persist(user);

    }
}
