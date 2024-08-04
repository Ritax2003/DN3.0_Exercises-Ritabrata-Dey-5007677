package com.library.repository;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    public void save(String bookTitle) {
        // Logic to save the book title (e.g., to a database)
        System.out.println("Book saved: " + bookTitle);
    }
}
