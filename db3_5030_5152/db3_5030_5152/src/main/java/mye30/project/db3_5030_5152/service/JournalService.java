package mye30.project.db3_5030_5152.service;

import mye30.project.db3_5030_5152.dataModel.*;
import java.util.List;

public interface JournalService {

    List<String> findAllJournals();

    JournalRanking findJournalRanking(String journal_name);

    List<Object[]> findNumOfJournalArticlesByYear(String journal_name);

    List<Object[]> findNumOfJournalAuthorsByYear(String journal_name);

    List<Object[]> findJournalByLastYear(String journal_name);

    List<Object[]> findJournalByFirstYear(String journal_name);

    Integer findNumOfJournalAuthors(String journal_name);

    Double findAvgAuthorsByJournal(String journal_name);

    Double findAvgAuthorsByYear(String journal_name);

    List<Article> findJournalArticles(String journal_name);

    List<Author> findJournalAuthors(String journal_name);

    Double findAvgJournalArticles(String journal_name);

    Integer findNumOfJournalAuthorsRange(String journal_name, int y1, int y2);

    Double findAvgAuthorsByJournalRange(String journal_name, int y1, int y2);

    Double findAvgAuthorsByYearRange(String journal_name, int y1, int y2);

    List<Article> findJournalArticlesRange(String journal_name, int y1, int y2);

    List<Author> findJournalAuthorsRange(String journal_name, int y1, int y2);

    Double findAvgJournalArticlesRange(String journal_name, int y1, int y2);

    // TODO REPORT

    List<Object[]> findNumOfJournalByCategory(String categoryName);

    Integer findPublisherPublications(String publisher);

    List<Object[]> findAvgAuthorsNumArticlesByYear(String journal_name);
}
