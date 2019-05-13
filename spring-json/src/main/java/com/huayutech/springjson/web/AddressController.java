package com.huayutech.springjson.web;

import com.huayutech.springjson.domain.Address;
import com.huayutech.springjson.domain.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Subgraph;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @GetMapping("/{addressId}")
    public Address doGetAddress(@PathVariable Long addressId){

        Address address = _doGetAddressMethod2(addressId);

        //User user = address.getUser();
        return address;
    }

    private Address _doGetAddressMethod1(Long addressId) {
        EntityGraph graph = entityManager.createEntityGraph(Address.class);
        Subgraph userGraph = graph.addSubgraph("user");

        Map hints = new HashMap();
        hints.put("javax.persistence.loadgraph", graph);

        Address address = entityManager.find(Address.class, addressId, hints);

        return address;
    }

    private Address _doGetAddressMethod2(Long addressId) {
        Address address = entityManager.find(Address.class, addressId);
        address.getUser().getName();
        return address;
    }

}
