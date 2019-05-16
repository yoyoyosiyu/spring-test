package com.huayutech.springpersistence.domain.association;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class Address {

    @Id
    Long id;

    @Column
    String country;

    @Column
    String province;

    @Column
    String city;

    @Column
    String area;


    @Column
    String street;


    @Column
    String postcode;


    @Column
    String name;


    @Column
    String title;


    @Column
    String phone;
}
