package com.pegimon.library.services.implementation;

import com.pegimon.library.models.Entities.PatronEntity;
import com.pegimon.library.repositories.PatronRepository;
import com.pegimon.library.services.PatronService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatronServiceImpl implements PatronService {
    private final PatronRepository patronRepository;

    public PatronServiceImpl(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }
    @Override
    public Optional<PatronEntity> updatePatron(Long id, PatronEntity patron) {
        if (patronRepository.existsById(id)) {
            patron.setId(id);
            return Optional.of(patronRepository.save(patron));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public PatronEntity addPatron(PatronEntity patron) {
        return patronRepository.save(patron);
    }

    @Override
    public List<PatronEntity> getAllPatrons() {
        return (List<PatronEntity>) patronRepository.findAll();
    }

    @Override
    public Optional<PatronEntity> getPatronById(Long id) {
        return patronRepository.findById(id);
    }

    @Override
    public Optional<PatronEntity> deletePatron(Long id) {
        Optional<PatronEntity> patron = patronRepository.findById(id);
        patron.ifPresent(patronRepository::delete);
        return patron;
    }
}
