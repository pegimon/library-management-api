package com.pegimon.library;

import com.pegimon.library.models.Entities.BookEntity;
import com.pegimon.library.models.Entities.BorrowingRecordEntity;
import com.pegimon.library.models.Entities.PatronEntity;
import com.pegimon.library.models.dto.BookDto;
import com.pegimon.library.models.dto.BorrowingRecordDto;
import com.pegimon.library.models.dto.PatronDto;

public class TestingUtils {
    private TestingUtils() {
    }

    public static BookEntity createBookEntityA() {
        return BookEntity.builder()
                .id(1L)
                .title("Title")
                .author("Author")
                .isbn("ISBN")
                .publicationYear("2021")
                .build();
    }

    public static BookEntity createBookEntityB() {
        return BookEntity.builder()
                .id(2L)
                .title("Title")
                .author("Author")
                .isbn("ISBN")
                .publicationYear("2022")
                .build();
    }

    public static BookEntity createBookEntityC() {
        return BookEntity.builder()
                .id(3L)
                .title("Title")
                .author("Author")
                .isbn("ISBN")
                .publicationYear("2023")
                .build();
    }

    public static BookDto createBookDto() {
        return BookDto.builder()
                .id(1L)
                .title("Title")
                .author("Author")
                .isbn("ISBN")
                .publicationYear("2021")
                .build();
    }

    public static PatronEntity createPatronEntityA() {
        return PatronEntity.builder()
                .id(1L)
                .name("Name")
                .email("Email")
                .phoneNumber("Phone Number")
                .build();
    }

    public static PatronEntity createPatronEntityB() {
        return PatronEntity.builder()
                .id(2L)
                .name("Name")
                .email("Email")
                .phoneNumber("Phone Number")
                .build();
    }

    public static PatronEntity createPatronEntityC() {
        return PatronEntity.builder()
                .id(3L)
                .name("Name")
                .email("Email")
                .phoneNumber("Phone Number")
                .build();
    }

    public static PatronDto createPatronDto() {
        return PatronDto.builder()
                .id(1L)
                .name("Name")
                .email("Email")
                .phoneNumber("Phone Number")
                .build();
    }

    public static BorrowingRecordEntity createBorrowingRecordEntityA() {
        return BorrowingRecordEntity.builder()
                .id(1L)
                .book(createBookEntityA())
                .patron(createPatronEntityA())
                .borrowingDate("2021-01-01")
                .returnDate("2021-01-02")
                .status("Borrowed")
                .build();
    }

    public static BorrowingRecordEntity createBorrowingRecordEntityB() {
        return BorrowingRecordEntity.builder()
                .id(2L)
                .book(createBookEntityB())
                .patron(createPatronEntityB())
                .borrowingDate("2021-01-01")
                .returnDate("2021-01-02")
                .status("Borrowed")
                .build();
    }

    public static BorrowingRecordEntity createBorrowingRecordEntityC() {
        return BorrowingRecordEntity.builder()
                .id(3L)
                .book(createBookEntityC())
                .patron(createPatronEntityC())
                .borrowingDate("2021-01-01")
                .returnDate("2021-01-02")
                .status("Borrowed")
                .build();
    }

    public static BorrowingRecordDto createBorrowingRecordDto() {
        return BorrowingRecordDto.builder()
                .id(1L)
                .book(createBookDto())
                .patron(createPatronDto())
                .borrowingDate("2021-01-01")
                .returnDate("2021-01-02")
                .status("Borrowed")
                .build();
    }
}
