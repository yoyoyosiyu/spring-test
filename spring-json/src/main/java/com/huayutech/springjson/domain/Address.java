package com.huayutech.springjson.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.huayutech.springjson.serializer.UserSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    String street;

    //@JsonSerialize(using = UserSerializer.class)
    @JsonIgnoreProperties(value={"addresses"})
    @ManyToOne(fetch = FetchType.LAZY)
    User user;

}
