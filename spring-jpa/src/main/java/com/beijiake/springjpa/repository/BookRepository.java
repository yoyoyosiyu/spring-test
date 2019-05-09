package com.beijiake.springjpa.repository;

import com.beijiake.springjpa.domain.Bidirectional.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
