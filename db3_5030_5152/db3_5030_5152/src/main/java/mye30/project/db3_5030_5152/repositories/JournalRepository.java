package mye30.project.db3_5030_5152.repositories;

import mye30.project.db3_5030_5152.dataModel.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Integer>{

    @Query("SELECT DISTINCT j.journal_name FROM Journal j")
    List<String> findAllJournals();

    @Query("SELECT jr FROM Journal j INNER JOIN JournalRanking jr ON j.journal_ID=jr.journal_ID WHERE j.journal_name=?1")
    JournalRanking findJournalRanking(String journal_name);

    @Query("SELECT ja.published_year, COUNT(DISTINCT art.article_ID) FROM Article art INNER JOIN JournalArticle ja ON art.article_ID=ja.article_ID WHERE ja.journal_name=?1 GROUP BY ja.published_year")
    List<Object[]> findNumOfJournalArticlesByYear(String journal_name);

    @Query("SELECT ja.published_year, COUNT(ath.author_ID), COUNT(DISTINCT ath.author_ID) FROM Author ath INNER JOIN JournalArticle ja ON ath.article_ID=ja.article_ID WHERE ja.journal_name=?1 GROUP BY ja.published_year")
    List<Object[]> findNumOfJournalAuthorsByYear(String journal_name);

    // STATS

    @Query("SELECT MAX(ja.published_year) FROM Journal j INNER JOIN JournalArticle ja ON j.journal_ID=ja.journal_ID WHERE j.journal_name=?1")
    Integer findJournalByLastYear(String journal_name);

    @Query("SELECT MIN(ja.published_year) FROM Journal j INNER JOIN JournalArticle ja ON j.journal_ID=ja.journal_ID WHERE j.journal_name=?1")
    Integer findJournalByFirstYear(String journal_name);

    @Query("SELECT COUNT(DISTINCT ath.author_ID) FROM Author ath INNER JOIN JournalArticle ja ON ath.article_ID=ja.article_ID WHERE ja.journal_name=?1")
    Integer findNumOfJournalAuthors(String journal_name);

    @Query("SELECT COUNT(ath.author_ID) * 1.0 / COUNT(DISTINCT ja.article_ID) FROM Author ath INNER JOIN JournalArticle ja ON ath.article_ID=ja.article_ID WHERE ja.journal_name=?1")
    Double findAvgAuthorsByJournal(String journal_name);

    @Query("SELECT COUNT(ath.author_ID) * 1.0 / COUNT(DISTINCT ja.published_year) FROM Author ath INNER JOIN JournalArticle ja ON ath.article_ID=ja.article_ID WHERE ja.journal_name=?1")
    Double findAvgAuthorsByYear(String journal_name);

    @Query("SELECT art FROM Article art INNER JOIN JournalArticle ja ON art.article_ID=ja.article_ID WHERE ja.journal_name=?1")
    List<Article> findJournalArticles(String journal_name);

    @Query("SELECT ath FROM Author ath INNER JOIN JournalArticle ja ON ath.article_ID=ja.article_ID WHERE ja.journal_name=?1")
    List<Author> findJournalAuthors(String journal_name);

    @Query("SELECT COUNT(art.article_ID) * 1.0 / COUNT(DISTINCT ja.published_year) FROM Article art INNER JOIN JournalArticle ja ON art.article_ID=ja.article_ID WHERE ja.journal_name=?1")
    Double findAvgJournalArticles(String journal_name);

    // YEAR RANGE QUERIES

    @Query("SELECT COUNT(DISTINCT ath.author_ID) FROM Author ath INNER JOIN JournalArticle ja ON ath.article_ID=ja.article_ID WHERE ja.journal_name=?1 AND ja.published_year>=?2 AND ja.published_year<=?3")
    Integer findNumOfJournalAuthorsRange(String journal_name, int y1, int y2);

    @Query("SELECT COUNT(ath.author_ID) * 1.0 / COUNT(DISTINCT ja.article_ID) FROM Author ath INNER JOIN JournalArticle ja ON ath.article_ID=ja.article_ID WHERE ja.journal_name=?1 AND ja.published_year>=?2 AND ja.published_year<=?3")
    Double findAvgAuthorsByJournalRange(String journal_name, int y1, int y2);

    @Query("SELECT COUNT(ath.author_ID) * 1.0 / COUNT(DISTINCT ja.published_year) FROM Author ath INNER JOIN JournalArticle ja ON ath.article_ID=ja.article_ID WHERE ja.journal_name=?1 AND ja.published_year>=?2 AND ja.published_year<=?3")
    Double findAvgAuthorsByYearRange(String journal_name, int y1, int y2);

    @Query("SELECT art FROM Article art INNER JOIN JournalArticle ja ON art.article_ID=ja.article_ID WHERE ja.journal_name=?1 AND ja.published_year>=?2 AND ja.published_year<=?3")
    List<Article> findJournalArticlesRange(String journal_name, int y1, int y2);

    @Query("SELECT ath FROM Author ath INNER JOIN JournalArticle ja ON ath.article_ID=ja.article_ID WHERE ja.journal_name=?1 AND ja.published_year>=?2 AND ja.published_year<=?3")
    List<Author> findJournalAuthorsRange(String journal_name, int y1, int y2);

    @Query("SELECT COUNT(art.article_ID) * 1.0 / COUNT(DISTINCT ja.published_year) FROM Article art INNER JOIN JournalArticle ja ON art.article_ID=ja.article_ID WHERE ja.journal_name=?1 AND ja.published_year>=?2 AND ja.published_year<=?3")
    Double findAvgJournalArticlesRange(String journal_name, int y1, int y2);

    // TODO REPORT

    @Query("SELECT  ja.published_year, COUNT(ja.journal_ID) FROM JournalRanking jr INNER JOIN JournalArticle ja ON jr.journal_ID=ja.journal_ID WHERE jr.bestSubjectArea=?1 GROUP BY ja.published_year")
    List<Object[]> findNumOfJournalByCategory(String categoryName);

    @Query("SELECT j.publisher, COUNT(j.journal_ID) FROM Journal j WHERE j.publisher=?1 GROUP BY j.publisher")
    Integer findPublisherPublications(String publisher);

    @Query("SELECT ja.published_year, COUNT(ath.author_ID) * 1.0 / COUNT(DISTINCT ja.article_ID), COUNT (DISTINCT ja.article_ID)FROM JournalArticle ja INNER JOIN Author ath ON ja.article_ID=ath.article_ID WHERE ja.journal_name=?1 GROUP BY ja.published_year")
    List<Object[]> findAvgAuthorsNumArticlesByYear(String journal_name);

    //@Query("SELECT ? FROM Journal j INNER JOIN JournalRanking jr ON j.journal_ID=jr.journal_ID WHERE j.journal_name=?1")
    //List<Object[]> findFromJournalRankings(String name, String n1, String n2);

}