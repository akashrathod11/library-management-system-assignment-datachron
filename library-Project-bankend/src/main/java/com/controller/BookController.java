	package com.controller;
	
	import java.util.List;
	
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.*;
	
	import com.model.Book;
	import com.service.BookService;
	
	@RestController
	@RequestMapping("/api/books")
	@CrossOrigin(origins = "http://localhost:4200")
	public class BookController {
	
	    private final BookService service;
	
	    public BookController(BookService service) {
	        this.service = service;
	    }
	
	    @PostMapping
	    public ResponseEntity<Book> addBook(@RequestBody Book book) {
	        return ResponseEntity.ok(service.addBook(book));
	    }
	
	    @GetMapping("/available")
	    public List<Book> getAvailableBooks() {
	        return service.getAllAvailableBooks();
	    }
	
	    @GetMapping("/search")
	    public List<Book> searchBooks(@RequestParam("q") String query) {
	        return service.searchBooks(query);
	    }
	
	    @PostMapping("/{id}/borrow")
	    public ResponseEntity<Book> borrowBook(@PathVariable Long id) {
	        return ResponseEntity.ok(service.borrowBook(id));
	    }
	
	    @PostMapping("/{id}/return")
	    public ResponseEntity<Book> returnBook(@PathVariable Long id) {
	        return ResponseEntity.ok(service.returnBook(id));
	    }
	}
