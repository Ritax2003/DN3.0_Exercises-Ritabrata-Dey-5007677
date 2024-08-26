package com.example.BookstoreAPI.service;



import com.example.BookstoreAPI.dto.BookDTO;
import com.example.BookstoreAPI.models.Book;
import com.example.BookstoreAPI.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);
        book = bookRepository.save(book);
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public BookDTO getBookById(int id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found"));
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO updateBook(int id, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found"));
        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setAuthor(bookDTO.getAuthor());
        existingBook.setPrice(bookDTO.getPrice());
        existingBook.setIsbn(bookDTO.getIsbn());
        bookRepository.save(existingBook);
        return modelMapper.map(existingBook, BookDTO.class);
    }

    @Override
    public void deleteBook(int id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Book not found");
        }
        bookRepository.deleteById(id);
    }
}
