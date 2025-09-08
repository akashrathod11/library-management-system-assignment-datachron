package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.model.Book;

public interface BookRepo extends JpaRepository<Book, Long> {
    List<Book> findByAvailableTrue();
    Book findByIsbn(String isbn);
    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author);
}
