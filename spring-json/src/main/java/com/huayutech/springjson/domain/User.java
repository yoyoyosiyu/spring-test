package com.huayutech.springjson.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity(name = "User")
public class User {

    @Id
    Long id;


    @Column
    String name;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    List<Address> addresses = new ArrayList<>();


}
