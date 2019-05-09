package com.beijiake.springjpa.command;

import com.beijiake.springjpa.domain.Bidirectional.Book;
import com.beijiake.springjpa.domain.Bidirectional.BookComment;
import com.beijiake.springjpa.repository.BookRepository;
import com.beijiake.springjpa.utils.RandomNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiElement;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import java.util.ArrayList;
import java.util.List;

@ShellComponent("book")
public class BookCommandComponent {

    @Autowired
    EntityManager entityManager;


    @Autowired
    BookRepository bookRepository;

    @Transactional
    @ShellMethod("create book")
    public String createBook() {

        Book book = new Book();


        book.setId(RandomNumber.random());
        book.setTitle("hello, world");

        BookComment bookComment = new BookComment("This is a good post!");
        book.addComment(bookComment);

        bookComment = new BookComment("good job!");
        book.addComment(bookComment);

        entityManager.persist(book);

        return "DONE";
    }

    @Transactional
    @ShellMethod("list books")
    public String listBook() {
        List<Book> books = entityManager.createQuery("SELECT book FROM Book book").getResultList();

        for (Book book: books) {
            System.out.println(AnsiOutput.toString(AnsiColor.GREEN, String.format("#ID: %d, #Title: %s", book.getId(), book.getTitle()), AnsiColor.DEFAULT));
            List<BookComment> comments = book.getComments();
            for (BookComment bookComment: comments) {
                System.out.println(AnsiOutput.toString(AnsiColor.BRIGHT_GREEN, String.format("\t#ID: %d, #content: %s", bookComment.getId(), bookComment.getContent()), AnsiColor.DEFAULT));
            }
        }

        return "DONE";
    }

    @Transactional
    @ShellMethod("delete a book")
    public String deleteBook(Long id) {
        Book book = entityManager.find(Book.class, id);

        entityManager.remove(book);

        return "DONE";
    }

    @Transactional
    @ShellMethod("delete a comment from book")
    public String deleteComment(Long id) {
        Book book = entityManager.find(Book.class, id);

        List<BookComment> comments = book.getComments();

        if (comments.size() > 0) {
            BookComment bookComment = comments.get(0);
            book.removeComment(bookComment);
        }

        //entityManager.persist(book);

        return "DONE";
    }


    @Transactional
    @ShellMethod("update a book comment")
    public String updateComment(Long id) {
        Book book = entityManager.find(Book.class, id);

        List<BookComment> comments = new ArrayList<>();

        BookComment comment = new BookComment("change to another");
        comment.setBook(book);

        comments.add(comment);

        book.setComments(comments);

        return "DONE";
    }

    @Transactional
    @ShellMethod("update a book use another method")
    public String updateBook(Long id) {

        //Book book = entityManager.find(Book.class, id);

        entityManager.setFlushMode(FlushModeType.AUTO);

        Book book1 = new Book();
        book1.setId(id);
        book1.addComment(new BookComment("good job!"));

        entityManager.merge(book1);

        //entityManager.persist(book);

        return "DONE";
    }

    @Transactional
    @ShellMethod("update a book use jpa")
    public String updateBookJpa(Long id) {

        //Book book = entityManager.find(Book.class, id);

        Book book1 = new Book();
        book1.setId(id);

        book1.addComment(new BookComment("good job!"));

        bookRepository.save(book1);

        //entityManager.persist(book);

        return "DONE";
    }


}
