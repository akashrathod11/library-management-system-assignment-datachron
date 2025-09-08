package com.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BookRepo;
import com.model.Book;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepo repo;

    public BookServiceImpl(BookRepo repo) {
        this.repo = repo;
    }

    @Override
    public Book addBook(Book book) {
        if (repo.findByIsbn(book.getIsbn()) != null) {
            throw new RuntimeException("ISBN already exists");
        }
        return repo.save(book);
    }

    @Override
    public List<Book> getAllAvailableBooks() {
        return repo.findByAvailableTrue();
    }

    @Override
    public List<Book> searchBooks(String query) {
        return repo.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query);
    }

    @Override
    @Transactional
    public Book borrowBook(Long id) {
        Book book = repo.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        if (!book.isAvailable()) {
            throw new RuntimeException("Book is already borrowed");
        }
        book.setAvailable(false);
        return repo.save(book);
    }

    @Override
    @Transactional
    public Book returnBook(Long id) {
        Book book = repo.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setAvailable(true);
        return repo.save(book);
    }
}
