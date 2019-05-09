package com.beijiake.springjpa.domain.Bidirectional;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Book {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String title;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<BookComment> comments = new ArrayList<>();

    public void setComments(List<BookComment> comments) {

        this.comments.clear();

        this.comments.addAll(comments);
    }


    public void addComment(BookComment bookComment) {
        bookComment.setBook(this);
        comments.add(bookComment);
    }

    public void removeComment(BookComment bookComment) {
        comments.remove(bookComment);
        bookComment.setBook(null);
    }

}
