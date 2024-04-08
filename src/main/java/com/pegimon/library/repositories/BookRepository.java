package com.pegimon.library.repositories;

import com.pegimon.library.models.Entities.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Long>{
}
