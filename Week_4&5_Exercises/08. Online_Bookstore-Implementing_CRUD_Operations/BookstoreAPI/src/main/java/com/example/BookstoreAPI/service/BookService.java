package com.example.BookstoreAPI.service;
import com.example.BookstoreAPI.dto.BookDTO;
import java.util.List;

public interface BookService {
    BookDTO createBook(BookDTO bookDTO);
    BookDTO getBookById(int id);
    List<BookDTO> getAllBooks();
    BookDTO updateBook(int id, BookDTO bookDTO);
    void deleteBook(int id);
}
