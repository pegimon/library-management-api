package com.pegimon.library.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pegimon.library.TestingUtils;
import com.pegimon.library.models.Entities.BorrowingRecordEntity;
import com.pegimon.library.services.BookService;
import com.pegimon.library.services.BorrowingRecordService;
import com.pegimon.library.services.PatronService;
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

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BorrowingRecordControllerIntegrationTest {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final BorrowingRecordService borrowingRecordService;
    private final BookService bookService;
    private final PatronService patronService;

    @Autowired
    public BorrowingRecordControllerIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper, BorrowingRecordService borrowingRecordService, BookService bookService, PatronService patronService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.borrowingRecordService = borrowingRecordService;
        this.bookService = bookService;
        this.patronService = patronService;
    }

    @Test
    public void testBorrowingBookReturns201() throws Exception {
        BorrowingRecordEntity testBorrowingRecord = TestingUtils.createBorrowingRecordEntityA();
        String borrowingRecordJson = objectMapper.writeValueAsString(testBorrowingRecord);
        bookService.addBook(testBorrowingRecord.getBook());
        patronService.addPatron(testBorrowingRecord.getPatron());
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/borrow/" + testBorrowingRecord.getBook().getId() + "/patron/" + testBorrowingRecord.getPatron().getId())
                        .contentType("application/json")
                        .content(borrowingRecordJson)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testReturningBookReturns200() throws Exception {
        BorrowingRecordEntity testBorrowingRecord = TestingUtils.createBorrowingRecordEntityA();
        String borrowingRecordJson = objectMapper.writeValueAsString(testBorrowingRecord);
        bookService.addBook(testBorrowingRecord.getBook());
        patronService.addPatron(testBorrowingRecord.getPatron());
        borrowingRecordService.borrowBookBorrowingRecord(testBorrowingRecord.getBook().getId(), testBorrowingRecord.getPatron().getId(), testBorrowingRecord.getReturnDate());
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/return/" + testBorrowingRecord.getBook().getId() + "/patron/" + testBorrowingRecord.getPatron().getId())
                        .contentType("application/json")
                        .content(borrowingRecordJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetAllBorrowingRecordsReturns200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/borrowing-records")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetBorrowingRecordByIdReturns200() throws Exception {
        BorrowingRecordEntity testBorrowingRecord = TestingUtils.createBorrowingRecordEntityA();
        bookService.addBook(testBorrowingRecord.getBook());
        patronService.addPatron(testBorrowingRecord.getPatron());
        BorrowingRecordEntity borrowingRecord = borrowingRecordService.borrowBookBorrowingRecord(testBorrowingRecord.getBook().getId(), testBorrowingRecord.getPatron().getId(), testBorrowingRecord.getReturnDate());
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/borrowing-records/" + borrowingRecord.getId())
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetBorrowingRecordByIdReturns404() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/borrowing-records/1")
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testBorrowBookReturnsTheRecord() throws Exception {
        BorrowingRecordEntity testBorrowingRecord = TestingUtils.createBorrowingRecordEntityA();
        String borrowingRecordJson = objectMapper.writeValueAsString(testBorrowingRecord);
        bookService.addBook(testBorrowingRecord.getBook());
        patronService.addPatron(testBorrowingRecord.getPatron());
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/borrow/" + testBorrowingRecord.getBook().getId() + "/patron/" + testBorrowingRecord.getPatron().getId())
                        .contentType("application/json")
                        .content(borrowingRecordJson)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Borrowed"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.book").value(testBorrowingRecord.getBook()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.patron").value(testBorrowingRecord.getPatron()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.borrowingDate").value(new SimpleDateFormat("dd-MM-yyyy").format(new Date()))
        );
    }

    @Test
    public void testReturnBookReturnsTheRecord() throws Exception {
        BorrowingRecordEntity testBorrowingRecord = TestingUtils.createBorrowingRecordEntityA();
        String borrowingRecordJson = objectMapper.writeValueAsString(testBorrowingRecord);
        bookService.addBook(testBorrowingRecord.getBook());
        patronService.addPatron(testBorrowingRecord.getPatron());
        borrowingRecordService.borrowBookBorrowingRecord(testBorrowingRecord.getBook().getId(), testBorrowingRecord.getPatron().getId(), testBorrowingRecord.getReturnDate());
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/return/" + testBorrowingRecord.getBook().getId() + "/patron/" + testBorrowingRecord.getPatron().getId())
                        .contentType("application/json")
                        .content(borrowingRecordJson)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Returned"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.book").value(testBorrowingRecord.getBook()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.patron").value(testBorrowingRecord.getPatron()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.returnDate").value(new SimpleDateFormat("dd-MM-yyyy").format(new Date()))
        );
    }

    @Test
    public void testGetAllBorrowingRecordsReturnsAllRecords() throws Exception {
        BorrowingRecordEntity testBorrowingRecordA = TestingUtils.createBorrowingRecordEntityA();
        BorrowingRecordEntity testBorrowingRecordB = TestingUtils.createBorrowingRecordEntityB();
        bookService.addBook(testBorrowingRecordA.getBook());
        patronService.addPatron(testBorrowingRecordA.getPatron());
        bookService.addBook(testBorrowingRecordB.getBook());
        patronService.addPatron(testBorrowingRecordB.getPatron());
        borrowingRecordService.borrowBookBorrowingRecord(testBorrowingRecordA.getBook().getId(), testBorrowingRecordA.getPatron().getId(), new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        borrowingRecordService.borrowBookBorrowingRecord(testBorrowingRecordB.getBook().getId(), testBorrowingRecordB.getPatron().getId(), new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/borrowing-records")
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].status").value("Borrowed"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].book").value(testBorrowingRecordA.getBook()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].patron").value(testBorrowingRecordA.getPatron()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].borrowingDate").value(new SimpleDateFormat("dd-MM-yyyy").format(new Date())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].status").value("Borrowed"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].book").value(testBorrowingRecordB.getBook()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].patron").value(testBorrowingRecordB.getPatron()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].borrowingDate").value(new SimpleDateFormat("dd-MM-yyyy").format(new Date())));
    }

    @Test
    public void testGetBorrowingRecordByIdReturnsTheRecord() throws Exception {
        BorrowingRecordEntity testBorrowingRecord = TestingUtils.createBorrowingRecordEntityA();
        bookService.addBook(testBorrowingRecord.getBook());
        patronService.addPatron(testBorrowingRecord.getPatron());
        BorrowingRecordEntity borrowingRecord = borrowingRecordService.borrowBookBorrowingRecord(testBorrowingRecord.getBook().getId(), testBorrowingRecord.getPatron().getId(), new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/borrowing-records/" + borrowingRecord.getId())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Borrowed"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.book").value(testBorrowingRecord.getBook()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.patron").value(testBorrowingRecord.getPatron()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.borrowingDate").value(new SimpleDateFormat("dd-MM-yyyy").format(new Date())));
    }
}
