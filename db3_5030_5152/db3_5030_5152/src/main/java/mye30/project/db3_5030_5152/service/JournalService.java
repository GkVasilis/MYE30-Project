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

    int findNumOfJournalAuthors(String journal_name);

    int findAvgAuthorsByJournal(String journal_name);

    int findAvgAuthorsByYear(String journal_name);

    List<Article> findJournalArticles(String journal_name);

    List<Author> findJournalAuthors(String journal_name);

    int findAvgJournalArticles(String journal_name);

    int findNumOfJournalAuthorsRange(String journal_name, int y1, int y2);

    int findAvgAuthorsByJournalRange(String journal_name, int y1, int y2);

    int findAvgAuthorsByYearRange(String journal_name, int y1, int y2);

    List<Article> findJournalArticlesRange(String journal_name, int y1, int y2);

    List<Author> findJournalAuthorsRange(String journal_name, int y1, int y2);

    int findAvgJournalArticlesRange(String journal_name, int y1, int y2);

    // TODO REPORT

    List<Object[]> findNumOfJournalByCategory(String categoryName);

    int findPublisherPublications(String publisher);

    List<Object[]> findAvgAuthorsNumArticlesByYear(String journal_name);
}
