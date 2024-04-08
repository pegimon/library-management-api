package com.pegimon.library.repositories;

import com.pegimon.library.TestingUtils;
import com.pegimon.library.models.Entities.BookEntity;
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
public class BookRepositoryTest {
    private final BookRepository bookRepository;

    @Autowired
    public BookRepositoryTest(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Test
    public void testThatBookCanBeCreated() {
        BookEntity testBookA = TestingUtils.createBookEntityA();
        bookRepository.save(testBookA);
        Optional<BookEntity> result = bookRepository.findById(testBookA.getId());
        assert result.isPresent();
        assert result.get().equals(testBookA);
    }

    @Test
    public void testThatBookCanBeUpdated() {
        BookEntity testBookA = TestingUtils.createBookEntityA();
        bookRepository.save(testBookA);
        testBookA.setTitle("Updated Title");
        bookRepository.save(testBookA);
        Optional<BookEntity> result = bookRepository.findById(testBookA.getId());
        assert result.isPresent();
        assert result.get().equals(testBookA);
    }

    @Test
    public void testThatBookCanBeDeleted() {
        BookEntity testBookA = TestingUtils.createBookEntityA();
        bookRepository.save(testBookA);
        bookRepository.deleteById(testBookA.getId());
        Optional<BookEntity> result = bookRepository.findById(testBookA.getId());
        assert result.isEmpty();
    }

    @Test
    public void testThatMultipleBooksCanBeCreated() {
        BookEntity testBookA = TestingUtils.createBookEntityA();
        BookEntity testBookB = TestingUtils.createBookEntityB();
        BookEntity testBookC = TestingUtils.createBookEntityC();
        bookRepository.save(testBookA);
        bookRepository.save(testBookB);
        bookRepository.save(testBookC);
        Optional<BookEntity> resultA = bookRepository.findById(testBookA.getId());
        Optional<BookEntity> resultB = bookRepository.findById(testBookB.getId());
        Optional<BookEntity> resultC = bookRepository.findById(testBookC.getId());
        assert resultA.isPresent();
        assert resultA.get().equals(testBookA);
        assert resultB.isPresent();
        assert resultB.get().equals(testBookB);
        assert resultC.isPresent();
        assert resultC.get().equals(testBookC);
    }
}
