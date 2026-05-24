package mye30.project.db3_5030_5152;
import mye30.project.db3_5030_5152.service.*;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;

@SpringBootTest //
public class TestService {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private YearService yearService;

    @Autowired
    private JournalService journalService;

    @Autowired
    private ConferenceService conferenceService;

    @Test
    void testAuthorServiceIsNotNull() {
        Assertions.assertNotNull(authorService);
    }

    @Test
    void testYearServiceIsNotNull() {
        Assertions.assertNotNull(yearService);
    }

    @Test
    void testJournalServiceIsNotNull() {
        Assertions.assertNotNull(journalService);
    }

    @Test
    void testConferenceServiceIsNotNull() {
        Assertions.assertNotNull(conferenceService);
    }

    @Test
    void testAuthorServiceFindAllAuthorsReturnsPaginatedPage() {
        Pageable pageable = PageRequest.of(0, 20);
        Page<String> authorsPage = authorService.findAllAuthors(pageable);
        Assertions.assertNotNull(authorsPage);
        Assertions.assertEquals(20, authorsPage.getSize());
    }

    @Test
    void testYearServiceFindAllYearsReturnsAllYearsList() {
        List<Integer> allYears = yearService.findAllYears();
        Assertions.assertNotNull(allYears);
    }

    @Test
    void testJournalServiceFindAllJournalsReturnsList() {
        List<String> allJournals = journalService.findAllJournals();
        Assertions.assertNotNull(allJournals);
    }

    @Test
    void testConferenceServiceFindAllConferencesReturnsList() {
        List<String> allConferences = conferenceService.findAllConferences();
        Assertions.assertNotNull(allConferences);
    }
}