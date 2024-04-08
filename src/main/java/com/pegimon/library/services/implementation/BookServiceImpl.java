package com.pegimon.library.services.implementation;

import com.pegimon.library.models.Entities.BookEntity;
import com.pegimon.library.repositories.BookRepository;
import com.pegimon.library.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Optional<BookEntity> UpdateBook(Long id, BookEntity book) {
        if (bookRepository.existsById(id)) {
            book.setId(id);
            return Optional.of(bookRepository.save(book));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public BookEntity addBook(BookEntity book) {
        return bookRepository.save(book);
    }

    @Override
    public List<BookEntity> getAllBooks() {
        return (List<BookEntity>) bookRepository.findAll();
    }

    @Override
    public Optional<BookEntity> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<BookEntity> deleteBook(Long id) {
        Optional<BookEntity> book = bookRepository.findById(id);
        book.ifPresent(bookRepository::delete);
        return book;
    }
}
