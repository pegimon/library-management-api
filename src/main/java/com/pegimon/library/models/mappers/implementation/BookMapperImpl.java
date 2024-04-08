package com.pegimon.library.models.mappers.implementation;

import com.pegimon.library.models.Entities.BookEntity;
import com.pegimon.library.models.dto.BookDto;
import com.pegimon.library.models.mappers.Mapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;


@Component
public class BookMapperImpl implements Mapper<BookEntity, BookDto> {
    @Override
    public BookDto map(@NotNull BookEntity object) {
        return new BookDto(object.getId(), object.getTitle(), object.getAuthor(), object.getIsbn(), object.getPublicationYear());
    }

    @Override
    public BookEntity reverse(@NotNull BookDto object) {
        return new BookEntity(object.getId(), object.getTitle(), object.getAuthor(), object.getIsbn(), object.getPublicationYear());
    }
}
