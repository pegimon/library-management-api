package com.pegimon.library.services.implementation;

import com.pegimon.library.models.Entities.BookEntity;
import com.pegimon.library.models.Entities.BorrowingRecordEntity;
import com.pegimon.library.models.Entities.PatronEntity;
import com.pegimon.library.repositories.BookRepository;
import com.pegimon.library.repositories.BorrowingRecordRepository;
import com.pegimon.library.repositories.PatronRepository;
import com.pegimon.library.services.BorrowingRecordService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowingRecordServiceImpl implements BorrowingRecordService {
    private final BorrowingRecordRepository borrowingRecordRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;

    public BorrowingRecordServiceImpl(BorrowingRecordRepository borrowingRecordRepository, BookRepository bookRepository, PatronRepository patronRepository) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }

    @Override
    public BorrowingRecordEntity borrowBookBorrowingRecord(Long BookId, Long PatronId, String dateBorrowed) {
        BookEntity book = bookRepository.findById(BookId).orElseThrow(() -> new RuntimeException("Book not found"));
        PatronEntity patron = patronRepository.findById(PatronId).orElseThrow(() -> new RuntimeException("Patron not found"));
        BorrowingRecordEntity borrowingRecord = new BorrowingRecordEntity(null, book, patron, dateBorrowed, null, "Borrowed");
        return borrowingRecordRepository.save(borrowingRecord);
    }

    @Override
    public BorrowingRecordEntity returnBookBorrowingRecord(Long BookId, Long PatronId, String dateReturned) {
        BookEntity book = bookRepository.findById(BookId).orElseThrow(() -> new RuntimeException("Book not found"));
        PatronEntity patron = patronRepository.findById(PatronId).orElseThrow(() -> new RuntimeException("Patron not found"));
        BorrowingRecordEntity borrowingRecord = borrowingRecordRepository.findByBookAndPatronAndStatus(book, patron, "Borrowed").orElseThrow(() -> new RuntimeException("Borrowing record not found"));
        borrowingRecord.setReturnDate(dateReturned);
        borrowingRecord.setStatus("Returned");
        return borrowingRecordRepository.save(borrowingRecord);
    }

    @Override
    public List<BorrowingRecordEntity> getAllBorrowingRecord() {
        return (List<BorrowingRecordEntity>) borrowingRecordRepository.findAll();
    }

    @Override
    public Optional<BorrowingRecordEntity> getBorrowingRecordById(Long id) {
        return borrowingRecordRepository.findById(id);
    }
}
