package mye30.project.db3_5030_5152.repositories;

import mye30.project.db3_5030_5152.dataModel.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Integer>{

    @Query("SELECT jr FROM Journal j INNER JOIN JournalRanking jr ON j.journal_ID=jr.journal_ID WHERE j.journal_name=?1")
    JournalRanking findJournalRanking(String journal_name);

    @Query("SELECT ja.published_year, COUNT(DISTINCT (art.article_ID)) FROM Article art INNER JOIN JournalArticle ja ON art.article_ID=ja.article_ID WHERE ja.journal_name=?1 GROUP BY ja.published_year")
    List<Object[]> findNumOfJournalArticlesByYear(String journal_name);

    @Query("SELECT ja.published_year, COUNT(ath.author_ID), COUNT(DISTINCT (ath.author_ID)) FROM Author ath INNER JOIN JournalArticle ja ON ath.article_ID=ja.article_ID WHERE ja.journal_name=?1 GROUP BY ja.published_year")
    List<Object[]> findNumOfJournalAuthorsByYear(String author_name);



}
