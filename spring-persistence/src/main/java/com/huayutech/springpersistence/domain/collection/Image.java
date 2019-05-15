package com.huayutech.springpersistence.domain.collection;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Setter
@Getter
@Embeddable
public class Image {

    @Column(nullable = true)
    String title;


    //@Column(nullable = false)
    //String filename;

    int width;

    int height;
}
