package com.pegimon.library.repositories;

import com.pegimon.library.models.Entities.PatronEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatronRepository extends CrudRepository<PatronEntity, Long>{
}
