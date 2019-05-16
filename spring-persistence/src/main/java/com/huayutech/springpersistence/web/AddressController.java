package com.huayutech.springpersistence.web;

import com.huayutech.springpersistence.domain.association.Address;
import com.huayutech.springpersistence.utils.RandomNumber;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("/addresses")
public class AddressController {


    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @PostMapping
    public ResponseEntity doCreateAddress() {

        Address address = new Address();

        address.setId(RandomNumber.random());

        address.setCountry("china");
        address.setProvince("HaiNan");
        address.setCity("HaiKou");
        address.setStreet("HaiDian Island SanXi Road QianHongMao");
        address.setName("Simon");
        address.setPhone("13807656053");
        address.setTitle("Mr");


        entityManager.persist(address);

        return new ResponseEntity(address, HttpStatus.OK);


    }

}
