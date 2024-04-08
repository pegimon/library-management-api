package com.pegimon.library.services;

import com.pegimon.library.models.Entities.BookEntity;
import com.pegimon.library.models.Entities.PatronEntity;

import java.util.List;
import java.util.Optional;

public interface PatronService {
    Optional<PatronEntity> updatePatron(Long id, PatronEntity patron);
    PatronEntity addPatron(PatronEntity patron);
    List<PatronEntity> getAllPatrons();
    Optional<PatronEntity> getPatronById(Long id);
    Optional<PatronEntity> deletePatron(Long id);
}
