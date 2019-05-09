package com.beijiake.springjpa.domain.Bidirectional;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"book_id", "content"}))
public class BookComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="book_id")
    Book book;

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (!(obj instanceof BookComment)) return false;

        //return id != null && id.equals(((BookComment) obj).getId());

        return content.equals(((BookComment) obj).getContent());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    public BookComment(String content) {
        this.content = content;
    }

    public BookComment() {
        this.content = null;
    }
}
