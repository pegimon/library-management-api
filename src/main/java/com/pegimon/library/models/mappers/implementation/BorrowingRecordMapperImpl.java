package com.pegimon.library.models.mappers.implementation;

import com.pegimon.library.models.Entities.BookEntity;
import com.pegimon.library.models.Entities.BorrowingRecordEntity;
import com.pegimon.library.models.Entities.PatronEntity;
import com.pegimon.library.models.dto.BookDto;
import com.pegimon.library.models.dto.BorrowingRecordDto;
import com.pegimon.library.models.dto.PatronDto;
import com.pegimon.library.models.mappers.Mapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BorrowingRecordMapperImpl implements Mapper<BorrowingRecordEntity, BorrowingRecordDto> {
    private final Mapper<BookEntity, BookDto> bookMapper;
    private final Mapper<PatronEntity, PatronDto> patronMapper;

    public BorrowingRecordMapperImpl(Mapper<BookEntity, BookDto> bookMapper, Mapper<PatronEntity, PatronDto> patronMapper) {
        this.bookMapper = bookMapper;
        this.patronMapper = patronMapper;
    }

    @Override
    public BorrowingRecordDto map(@NotNull BorrowingRecordEntity object) {
        return new BorrowingRecordDto(object.getId(), bookMapper.map(object.getBook()), patronMapper.map(object.getPatron()), object.getBorrowingDate(), object.getReturnDate(), object.getStatus());
    }

    @Override
    public BorrowingRecordEntity reverse(@NotNull BorrowingRecordDto object) {
        return new BorrowingRecordEntity(object.getId(), bookMapper.reverse(object.getBook()), patronMapper.reverse(object.getPatron()), object.getBorrowingDate(), object.getReturnDate(), object.getStatus());
    }
}
