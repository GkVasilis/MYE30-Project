package mye30.project.db3_5030_5152.repositories;

import mye30.project.db3_5030_5152.dataModel.*;
import org.hibernate.annotations.View;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YearRepository extends JpaRepository<Article, Integer>{

    @Query("SELECT DISTINCT(art.published_year) FROM Article art ORDER BY art.published_year ASC")
    List<Integer> findAllYears();

    @Query("SELECT COUNT(art.article_ID) FROM Article art WHERE art.published_year = ?1 GROUP BY art.article_ID")
    Integer findPublishedArticles(int year);

    @Query("SELECT COUNT(j.journal_ID) FROM Article art INNER JOIN JournalArticle j ON art.article_ID= j.article_ID WHERE art.published_year = ?1 GROUP BY j.journal_ID")
    Integer findNumOfJournals(int year);

    @Query("SELECT COUNT(cnf.conference_ID) FROM Article art INNER JOIN ConferenceArticle cnf ON art.article_ID= cnf.article_ID WHERE art.published_year = ?1")
    Integer findNumOfConferences(int year);

    @Query("SELECT COUNT(ath.author_ID) * 1.0, COUNT(DISTINCT ath.author_ID) FROM Article art INNER JOIN Author ath ON art.article_ID= ath.article_ID WHERE art.published_year = ?1")
    List<Object[]> findNumOfAuthors(int year);

    @Query("SELECT art.published_year, art.title, j.journal_name  FROM Article art INNER JOIN JournalArticle j ON art.article_ID= j.article_ID WHERE j.journal_name = ?2 AND  art.published_year = ?1")
    List<Object[]> findPublicationByJournal(int year, String jName);

    @Query("SELECT art.published_year, art.title, c.conference_name  FROM Article art INNER JOIN ConferenceArticle c ON art.article_ID= c.article_ID WHERE c.conference_name = ?2 AND  art.published_year = ?1")
    List<Object[]> findPublicationByConference(int year, String cName);

    @Query("SELECT art.published_year, art.title, ath.author_name FROM Article art INNER JOIN Author ath ON art.article_ID= ath.article_ID WHERE ath.author_name = ?2 AND  art.published_year = ?1")
    List<Object[]> findPublicationByAuthor(int year, String aName);
}


