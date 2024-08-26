package com.example.BookstoreAPI.repository;



import com.example.BookstoreAPI.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
