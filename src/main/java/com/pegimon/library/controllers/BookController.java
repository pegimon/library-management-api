package com.pegimon.library.controllers;

import com.pegimon.library.models.Entities.BookEntity;
import com.pegimon.library.models.dto.BookDto;
import com.pegimon.library.models.mappers.Mapper;
import com.pegimon.library.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    private final Mapper<BookEntity, BookDto> bookMapper;

    public BookController(BookService bookService, Mapper<BookEntity, BookDto> bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }
    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
        BookEntity bookEntity = bookMapper.reverse(bookDto);
        BookEntity savedBook = bookService.addBook(bookEntity);
        return new ResponseEntity<>(bookMapper.map(savedBook), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        BookEntity bookEntity = bookMapper.reverse(bookDto);
        BookEntity updatedBook = bookService.UpdateBook(id, bookEntity);
        return ResponseEntity.ok(bookMapper.map(updatedBook));
    }

    @GetMapping
    public List<BookDto> getAllBooks() {
        List<BookEntity> books = bookService.getAllBooks();
        return books.stream().map(bookMapper::map).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(bookMapper::map)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDto> deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id)
                .map(bookMapper::map)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
