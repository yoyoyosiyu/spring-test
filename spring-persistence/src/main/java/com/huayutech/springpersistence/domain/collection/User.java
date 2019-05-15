package com.huayutech.springpersistence.domain.collection;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @Column
    String name;


    @ElementCollection
    @CollectionTable
    @MapKeyColumn(name = "`key`")
    @Column(name = "`value`")
    Map<String, String> attributes = new HashMap<>();


    @ElementCollection
    @CollectionTable
    @MapKeyColumn(name="filename")
    Map<String, Image> images = new HashMap<>();


    @ElementCollection
    @CollectionTable
    Map<Filename, Image> detailsImages = new HashMap<>();


}
