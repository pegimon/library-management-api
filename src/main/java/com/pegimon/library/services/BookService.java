package com.pegimon.library.services;

import com.pegimon.library.models.Entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity UpdateBook(Long id, BookEntity book);
    BookEntity addBook(BookEntity book);
    List<BookEntity> getAllBooks();
    Optional<BookEntity> getBookById(Long id);
    Optional<BookEntity> deleteBook(Long id);
}
