package com.pegimon.library.repositories;

import com.pegimon.library.models.Entities.BookEntity;
import com.pegimon.library.models.Entities.BorrowingRecordEntity;
import com.pegimon.library.models.Entities.PatronEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BorrowingRecordRepository extends CrudRepository<BorrowingRecordEntity, Long> {
    Optional<BorrowingRecordEntity> findByBookAndPatronAndStatus(BookEntity book, PatronEntity patron, String borrowed);
}
