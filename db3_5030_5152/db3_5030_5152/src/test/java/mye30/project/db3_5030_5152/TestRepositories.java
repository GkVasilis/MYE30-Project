package mye30.project.db3_5030_5152;

import mye30.project.db3_5030_5152.repositories.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;

@SpringBootTest
public class TestRepositories {

    @Autowired
    AuthorRepositoty authorRepository;

    @Autowired
    YearRepository yearRepository;

    @Autowired
    JournalRepository journalRepository;

    @Autowired
    ConferenceRepository conferenceRepository;

    @Test
    void testAuthorRepositoryIsNotNull() {
        Assertions.assertNotNull(authorRepository);
    }

    @Test
    void testYearRepositoryIsNotNull() {
        Assertions.assertNotNull(yearRepository);
    }

    @Test
    void testJournalRepositoryIsNotNull() {
        Assertions.assertNotNull(journalRepository);
    }

    @Test
    void testConferenceRepositoryIsNotNull() {
        Assertions.assertNotNull(conferenceRepository);
    }

    @Test
    void testAuthorFindAllAuthorsReturnsPaginatedPage() {
        Pageable pageable = PageRequest.of(0, 20);
        Page<String> authorsPage = authorRepository.findAllAuthors(pageable);
        Assertions.assertNotNull(authorsPage);
        Assertions.assertEquals(20, authorsPage.getSize());
    }

    @Test
    void testYearFindAllYearsReturnsAllYearsList() {
        List<Integer> allYears = yearRepository.findAllYears();
        Assertions.assertNotNull(allYears);
    }

    @Test
    void testJournalFindAllJournalsReturnsList() {
        List<String> allJournals = journalRepository.findAllJournals();
        Assertions.assertNotNull(allJournals);
    }

    @Test
    void testConferenceFindAllConferencesReturnsList() {
        List<String> allConferences = conferenceRepository.findAllConferences();
        Assertions.assertNotNull(allConferences);
    }
}
