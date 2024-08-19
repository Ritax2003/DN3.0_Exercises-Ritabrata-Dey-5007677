package com.example.BookstoreAPI.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.BookstoreAPI.models.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    // In-memory list to store books
    private final List<Book> books = new ArrayList<>();

    // POST method to create a new book
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)  // Status 201 Created
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        books.add(book);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("X-Book-Created", "true")
                .body(book);
    }

    // GET method to fetch a list of books
    @GetMapping
    @ResponseStatus(HttpStatus.OK)  // Status 200 OK
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok()
                .header("X-Books-Count", String.valueOf(books.size()))
                .body(books);
    }

    // GET method to fetch a book by ID with custom headers
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = books.stream()
                .filter(b -> b.getId()==(id))
                .findFirst();

        if (book.isPresent()) {
            return ResponseEntity.ok()
                    .header("X-Custom-Header", "Book Found")
                    .body(book.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("X-Custom-Error", "Book Not Found")
                    .build();
        }
    }
}
