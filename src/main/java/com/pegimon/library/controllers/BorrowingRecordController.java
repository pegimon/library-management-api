package com.pegimon.library.controllers;

import com.pegimon.library.models.Entities.BorrowingRecordEntity;
import com.pegimon.library.models.dto.BorrowingRecordDto;
import com.pegimon.library.models.mappers.Mapper;
import com.pegimon.library.services.BorrowingRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BorrowingRecordController {
    private final BorrowingRecordService borrowingRecordService;
    private final Mapper<BorrowingRecordEntity, BorrowingRecordDto> borrowingRecordMapper;

    public BorrowingRecordController(BorrowingRecordService borrowingRecordService, Mapper<BorrowingRecordEntity, BorrowingRecordDto> borrowingRecordMapper) {
        this.borrowingRecordService = borrowingRecordService;
        this.borrowingRecordMapper = borrowingRecordMapper;
    }

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecordDto> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        BorrowingRecordEntity borrowingRecordEntity = borrowingRecordService.borrowBookBorrowingRecord(bookId, patronId, new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        return new ResponseEntity<>(borrowingRecordMapper.map(borrowingRecordEntity), HttpStatus.CREATED);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecordDto> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        BorrowingRecordEntity borrowingRecordEntity = borrowingRecordService.returnBookBorrowingRecord(bookId, patronId, new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        return ResponseEntity.ok(borrowingRecordMapper.map(borrowingRecordEntity));
    }

    @GetMapping("/borrowing-records")
    public List<BorrowingRecordDto> getAllBorrowingRecords() {
        List<BorrowingRecordEntity> borrowingRecords = borrowingRecordService.getAllBorrowingRecord();
        return borrowingRecords.stream().map(borrowingRecordMapper::map).toList();
    }

    @GetMapping("/borrowing-records/{id}")
    public ResponseEntity<BorrowingRecordDto> getBorrowingRecordById(@PathVariable Long id) {
        return borrowingRecordService.getBorrowingRecordById(id)
                .map(borrowingRecordMapper::map)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
