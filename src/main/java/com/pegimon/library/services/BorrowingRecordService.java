package com.pegimon.library.services;

import com.pegimon.library.models.Entities.BorrowingRecordEntity;

import java.util.List;
import java.util.Optional;

public interface BorrowingRecordService {
    BorrowingRecordEntity returnBookBorrowingRecord(Long BookId, Long PatronId, String dateReturned);
    BorrowingRecordEntity borrowBookBorrowingRecord(Long BookId, Long PatronId, String dateBorrowed);
    List<BorrowingRecordEntity> getAllBorrowingRecord();
    Optional<BorrowingRecordEntity> getBorrowingRecordById(Long id);
}
