package com.pegimon.library.models.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "borrowing_records")
public class BorrowingRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "borrowing_record_id_seq")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private BookEntity book;
    @ManyToOne
    @JoinColumn(name = "patron_id", referencedColumnName = "id")
    private PatronEntity patron;
    private String borrowingDate;
    private String returnDate;
    private String status;
}
