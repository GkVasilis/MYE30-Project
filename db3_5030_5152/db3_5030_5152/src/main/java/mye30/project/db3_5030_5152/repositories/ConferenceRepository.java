package mye30.project.db3_5030_5152.repositories;

import mye30.project.db3_5030_5152.dataModel.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Integer>{

    @Query("SELECT c.conference_name FROM Conference c")
    List<String> findAllConferences();

    @Query("SELECT cr FROM Conference c INNER JOIN ConferenceRanking cr ON c.conference_ID=cr.conference_ID WHERE c.conference_ID=?1")
    ConferenceRanking findConferenceRanking(String conf_name);

    @Query("SELECT ca.published_year, COUNT(DISTINCT (art.article_ID)) FROM Article art INNER JOIN ConferenceArticle ca ON art.article_ID=ca.article_ID WHERE ca.conference_name=?1 GROUP BY ca.published_year")
    List<Object[]> findNumOfConferenceArticlesByYear(String conf_name);

    @Query("SELECT ca.published_year, COUNT(ath.author_ID), COUNT(DISTINCT (ath.author_ID)) FROM Author ath INNER JOIN ConferenceArticle ca ON ath.article_ID=ca.article_ID WHERE ca.conference_name=?1 GROUP BY ca.published_year")
    List<Object[]> findNumOfConferenceAuthorsByYear(String author_name);

    // STATS

    @Query("SELECT  c , MAX(ca.published_year) FROM Conference c INNER JOIN ConferenceArticle ca ON c.conference_ID=ca.conference_ID WHERE c.conference_name=?1")
    List<Object[]> findConferenceByLastYear(String conf_name);

    @Query("SELECT  c , MIN(ca.published_year) FROM Conference c INNER JOIN ConferenceArticle ca ON c.conference_ID=ca.conference_ID WHERE c.conference_name=?1")
    List<Object[]> findConferenceByFirstYear(String conf_name);

    @Query("SELECT COUNT(DISTINCT(ath.author_ID)) FROM Author ath INNER JOIN ConferenceArticle ca ON ath.article_ID=ca.article_ID WHERE ca.conference_name=?1")
    int findNumOfConferenceAuthors(String conf_name);

    @Query("SELECT COUNT(ath.author_ID) / COUNT(ca.article_ID) FROM Author ath INNER JOIN ConferenceArticle ca ON ath.article_ID=ca.article_ID WHERE ca.conference_name=?1")
    int findAvgAuthorsByConference(String conf_name);

    @Query("SELECT COUNT(ath.author_ID) / COUNT(ca.published_year) FROM Author ath INNER JOIN ConferenceArticle ca ON ath.article_ID=ca.article_ID WHERE ca.conference_name=?1")
    int findAvgAuthorsByYear(String conf_name);

    @Query("SELECT art FROM Article art INNER JOIN ConferenceArticle ca ON art.article_ID=ca.article_ID WHERE ca.conference_name=?1")
    List<Article>  findConferenceArticles(String conf_name);

}
