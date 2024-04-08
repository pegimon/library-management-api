package com.pegimon.library.repositories;

import com.pegimon.library.TestingUtils;
import com.pegimon.library.models.Entities.BorrowingRecordEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BorrowingRecordRepositoryTest {
    private final BorrowingRecordRepository borrowingRecordRepository;
    private final PatronRepository patronRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BorrowingRecordRepositoryTest(BorrowingRecordRepository borrowingRecordRepository, PatronRepository patronRepository, BookRepository bookRepository) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.patronRepository = patronRepository;
        this.bookRepository = bookRepository;
    }

    @Test
    public void testThatBorrowingRecordCanBeCreated() {
        BorrowingRecordEntity testBorrowingRecord = TestingUtils.createBorrowingRecordEntityA();
        bookRepository.save(testBorrowingRecord.getBook());
        patronRepository.save(testBorrowingRecord.getPatron());
        borrowingRecordRepository.save(testBorrowingRecord);
        Optional<BorrowingRecordEntity> result = borrowingRecordRepository.findById(testBorrowingRecord.getId());
        assert result.isPresent();
        assert result.get().equals(testBorrowingRecord);
    }

    @Test
    public void testThatBorrowingRecordCanBeUpdated() {
        BorrowingRecordEntity testBorrowingRecord = TestingUtils.createBorrowingRecordEntityA();
        bookRepository.save(testBorrowingRecord.getBook());
        patronRepository.save(testBorrowingRecord.getPatron());
        borrowingRecordRepository.save(testBorrowingRecord);
        testBorrowingRecord.setStatus("Returned");
        borrowingRecordRepository.save(testBorrowingRecord);
        Optional<BorrowingRecordEntity> result = borrowingRecordRepository.findById(testBorrowingRecord.getId());
        assert result.isPresent();
        assert result.get().equals(testBorrowingRecord);
    }

    @Test
    public void testThatBorrowingRecordCanBeDeleted() {
        BorrowingRecordEntity testBorrowingRecord = TestingUtils.createBorrowingRecordEntityA();
        bookRepository.save(testBorrowingRecord.getBook());
        patronRepository.save(testBorrowingRecord.getPatron());
        borrowingRecordRepository.save(testBorrowingRecord);
        borrowingRecordRepository.deleteById(testBorrowingRecord.getId());
        Optional<BorrowingRecordEntity> result = borrowingRecordRepository.findById(testBorrowingRecord.getId());
        assert result.isEmpty();
    }

    @Test
    public void testThatMultipleBorrowingRecordsCanBeCreated() {
        BorrowingRecordEntity testBorrowingRecordA = TestingUtils.createBorrowingRecordEntityA();
        BorrowingRecordEntity testBorrowingRecordB = TestingUtils.createBorrowingRecordEntityB();
        BorrowingRecordEntity testBorrowingRecordC = TestingUtils.createBorrowingRecordEntityC();
        bookRepository.save(testBorrowingRecordA.getBook());
        bookRepository.save(testBorrowingRecordB.getBook());
        bookRepository.save(testBorrowingRecordC.getBook());
        patronRepository.save(testBorrowingRecordA.getPatron());
        patronRepository.save(testBorrowingRecordB.getPatron());
        patronRepository.save(testBorrowingRecordC.getPatron());
        borrowingRecordRepository.save(testBorrowingRecordA);
        borrowingRecordRepository.save(testBorrowingRecordB);
        borrowingRecordRepository.save(testBorrowingRecordC);
        Optional<BorrowingRecordEntity> resultA = borrowingRecordRepository.findById(testBorrowingRecordA.getId());
        Optional<BorrowingRecordEntity> resultB = borrowingRecordRepository.findById(testBorrowingRecordB.getId());
        Optional<BorrowingRecordEntity> resultC = borrowingRecordRepository.findById(testBorrowingRecordC.getId());
        assert resultA.isPresent();
        assert resultB.isPresent();
        assert resultC.isPresent();
        assert resultA.get().equals(testBorrowingRecordA);
        assert resultB.get().equals(testBorrowingRecordB);
        assert resultC.get().equals(testBorrowingRecordC);
    }
}
