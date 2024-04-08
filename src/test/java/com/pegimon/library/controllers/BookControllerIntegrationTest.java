package com.pegimon.library.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pegimon.library.TestingUtils;
import com.pegimon.library.models.Entities.BookEntity;
import com.pegimon.library.models.dto.BookDto;
import com.pegimon.library.services.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final BookService bookService;

    @Autowired
    public BookControllerIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper, BookService bookService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.bookService = bookService;
    }

    @Test
    public void testCreateBookReturns201() throws Exception {
        BookEntity testBookA = TestingUtils.createBookEntityA();
        testBookA.setId(null);
        String bookJson = objectMapper.writeValueAsString(testBookA);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/books")
                        .contentType("application/json")
                        .content(bookJson)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testThatUpdateBookReturns200() throws Exception {
        BookEntity testBookA = TestingUtils.createBookEntityA();
        bookService.addBook(testBookA);
        testBookA.setTitle("Updated Title");
        String bookJson = objectMapper.writeValueAsString(testBookA);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/books/" + testBookA.getId())
                        .contentType("application/json")
                        .content(bookJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatDeleteBookReturns200() throws Exception {
        BookEntity testBookA = TestingUtils.createBookEntityA();
        bookService.addBook(testBookA);
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/books/" + testBookA.getId())
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetAllBooksReturns200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/books")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetBookByIdReturns200() throws Exception {
        BookEntity testBookA = TestingUtils.createBookEntityA();
        bookService.addBook(testBookA);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/books/" + testBookA.getId())
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetBookByIdReturns404() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/books/1")
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testCreateBookCreatesBook() throws Exception {
        BookDto testBookA = TestingUtils.createBookDto();
        String bookJson = objectMapper.writeValueAsString(testBookA);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/books")
                        .contentType("application/json")
                        .content(bookJson)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(testBookA.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value(testBookA.getAuthor()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(testBookA.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.publicationYear").value(testBookA.getPublicationYear()));
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/books/" + testBookA.getId())
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(bookJson));
    }

    @Test
    public void testUpdateBookUpdatesBook() throws Exception {
        BookEntity testBookA = TestingUtils.createBookEntityA();
        bookService.addBook(testBookA);
        testBookA.setTitle("Updated Title");
        String bookJson = objectMapper.writeValueAsString(testBookA);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/books/" + testBookA.getId())
                        .contentType("application/json")
                        .content(bookJson)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(testBookA.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value(testBookA.getAuthor()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(testBookA.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.publicationYear").value(testBookA.getPublicationYear()));
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/books/" + testBookA.getId())
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(bookJson));
    }

    @Test
    public void testDeleteBookDeletesBook() throws Exception {
        BookEntity testBookA = TestingUtils.createBookEntityA();
        bookService.addBook(testBookA);
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/books/" + testBookA.getId())
        ).andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/books/" + testBookA.getId())
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetAllBooksReturnsAllBooks() throws Exception {
        BookEntity testBookA = TestingUtils.createBookEntityA();
        BookEntity testBookB = TestingUtils.createBookEntityB();
        BookEntity testBookC = TestingUtils.createBookEntityC();
        bookService.addBook(testBookA);
        bookService.addBook(testBookB);
        bookService.addBook(testBookC);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/books")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value(testBookA.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author").value(testBookA.getAuthor()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].isbn").value(testBookA.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].publicationYear").value(testBookA.getPublicationYear()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value(testBookB.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].author").value(testBookB.getAuthor()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].isbn").value(testBookB.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].publicationYear").value(testBookB.getPublicationYear()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].title").value(testBookC.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].author").value(testBookC.getAuthor()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].isbn").value(testBookC.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].publicationYear").value(testBookC.getPublicationYear()));
    }

}
