package com.example.BookstoreAPI.controllers;

import com.example.BookstoreAPI.models.Book;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> bookList = new ArrayList<>();

    // Sample data
    public BookController() {
        bookList.add(new Book(1, "1984", "George Orwell", 9.99, "1234567890"));
        bookList.add(new Book(2, "Brave New World", "Aldous Huxley", 12.99, "0987654321"));
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookList;
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookList.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        bookList.add(book);
        return book;
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        Book book = bookList.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setPrice(updatedBook.getPrice());
        book.setIsbn(updatedBook.getIsbn());
        return book;
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        Book book = bookList.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Book not found"));

        bookList.remove(book);
        return "Book deleted successfully!";
    }
}