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
    public BookEntity UpdateBook(Long id, BookEntity book) {
        return bookRepository.findById(id).map(desiredBook -> {
            Optional.ofNullable(book.getTitle()).ifPresent(desiredBook::setTitle);
            Optional.ofNullable(book.getAuthor()).ifPresent(desiredBook::setAuthor);
            Optional.ofNullable(book.getIsbn()).ifPresent(desiredBook::setIsbn);
            Optional.ofNullable(book.getPublicationYear()).ifPresent(desiredBook::setPublicationYear);
            return bookRepository.save(desiredBook);
        }).orElseThrow(() -> new RuntimeException("Book not found"));
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
