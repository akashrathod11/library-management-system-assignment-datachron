package com.service;

import java.util.List;
import com.model.Book;

public interface BookService {
    Book addBook(Book book);
    List<Book> getAllAvailableBooks();
    List<Book> searchBooks(String query);
    Book borrowBook(Long id);
    Book returnBook(Long id);
}
