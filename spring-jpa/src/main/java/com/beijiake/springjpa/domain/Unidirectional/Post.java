package com.beijiake.springjpa.domain.Unidirectional;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Post {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String title;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<PostComment> comments = new ArrayList<>();


}
