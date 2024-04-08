package com.pegimon.library.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowingRecordDto {
    private Long id;
    private BookDto book;
    private PatronDto patron;
    private String borrowingDate;
    private String returnDate;
    private String status;
}
