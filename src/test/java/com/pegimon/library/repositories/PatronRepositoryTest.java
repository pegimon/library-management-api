package com.pegimon.library.repositories;

import com.pegimon.library.TestingUtils;
import com.pegimon.library.models.Entities.PatronEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PatronRepositoryTest {
    private final PatronRepository patronRepository;

    @Autowired
    public PatronRepositoryTest(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    @Test
    public void testThatPatronCanBeCreated() {
        PatronEntity testPatronA = TestingUtils.createPatronEntityA();
        patronRepository.save(testPatronA);
        Optional<PatronEntity> result = patronRepository.findById(testPatronA.getId());
        assert result.isPresent();
        assert result.get().equals(testPatronA);
    }

    @Test
    public void testThatPatronCanBeUpdated() {
        PatronEntity testPatronA = TestingUtils.createPatronEntityA();
        patronRepository.save(testPatronA);
        testPatronA.setName("Updated Name");
        patronRepository.save(testPatronA);
        Optional<PatronEntity> result = patronRepository.findById(testPatronA.getId());
        assert result.isPresent();
        assert result.get().equals(testPatronA);
    }

    @Test
    public void testThatPatronCanBeDeleted() {
        PatronEntity testPatronA = TestingUtils.createPatronEntityA();
        patronRepository.save(testPatronA);
        patronRepository.deleteById(testPatronA.getId());
        Optional<PatronEntity> result = patronRepository.findById(testPatronA.getId());
        assert result.isEmpty();
    }

    @Test
    public void testThatMultiplePatronsCanBeCreated() {
        PatronEntity testPatronA = TestingUtils.createPatronEntityA();
        PatronEntity testPatronB = TestingUtils.createPatronEntityB();
        PatronEntity testPatronC = TestingUtils.createPatronEntityC();
        patronRepository.save(testPatronA);
        patronRepository.save(testPatronB);
        patronRepository.save(testPatronC);
        Optional<PatronEntity> resultA = patronRepository.findById(testPatronA.getId());
        Optional<PatronEntity> resultB = patronRepository.findById(testPatronB.getId());
        Optional<PatronEntity> resultC = patronRepository.findById(testPatronC.getId());
        assert resultA.isPresent();
        assert resultA.get().equals(testPatronA);
        assert resultB.isPresent();
        assert resultB.get().equals(testPatronB);
        assert resultC.isPresent();
        assert resultC.get().equals(testPatronC);
    }

}
