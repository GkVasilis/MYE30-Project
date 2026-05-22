package mye30.project.db3_5030_5152.repositories;

import mye30.project.db3_5030_5152.dataModel.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.query.JpqlQueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Integer>{

    @Query("SELECT DISTINCT c.conference_name FROM Conference c")
    List<String> findAllConferences();

    @Query("SELECT cr FROM Conference c INNER JOIN ConferenceRanking cr ON c.conference_ID=cr.conference_ID WHERE c.conference_name=?1")
    ConferenceRanking findConferenceRanking(String conf_name);

    @Query("SELECT ca.published_year, COUNT(DISTINCT art.article_ID) FROM Article art INNER JOIN ConferenceArticle ca ON art.article_ID=ca.article_ID WHERE ca.conference_name=?1 GROUP BY ca.published_year")
    List<Object[]> findNumOfConferenceArticlesByYear(String conf_name);

    @Query("SELECT ca.published_year, COUNT(ath.author_ID), COUNT(DISTINCT ath.author_ID) FROM Author ath INNER JOIN ConferenceArticle ca ON ath.article_ID=ca.article_ID WHERE ca.conference_name=?1 GROUP BY ca.published_year")
    List<Object[]> findNumOfConferenceAuthorsByYear(String conf_name);

    // STATS

    @Query("SELECT  c , MAX(ca.published_year) FROM Conference c INNER JOIN ConferenceArticle ca ON c.conference_ID=ca.conference_ID WHERE c.conference_name=?1 GROUP BY c")
    List<Object[]> findConferenceByLastYear(String conf_name);

    @Query("SELECT  c , MIN(ca.published_year) FROM Conference c INNER JOIN ConferenceArticle ca ON c.conference_ID=ca.conference_ID WHERE c.conference_name=?1 GROUP BY c")
    List<Object[]> findConferenceByFirstYear(String conf_name);

    @Query("SELECT COUNT(DISTINCT ath.author_ID) FROM Author ath INNER JOIN ConferenceArticle ca ON ath.article_ID=ca.article_ID WHERE ca.conference_name=?1")
    Integer findNumOfConferenceAuthors(String conf_name);

    @Query("SELECT COUNT(ath.author_ID) * 1.0 / COUNT(ca.article_ID) FROM Author ath INNER JOIN ConferenceArticle ca ON ath.article_ID=ca.article_ID WHERE ca.conference_name=?1")
    Double findAvgAuthorsByConference(String conf_name);

    @Query("SELECT COUNT(ath.author_ID) * 1.0 / COUNT(ca.published_year) FROM Author ath INNER JOIN ConferenceArticle ca ON ath.article_ID=ca.article_ID WHERE ca.conference_name=?1")
    Double findAvgAuthorsByYear(String conf_name);

    @Query("SELECT art FROM Article art INNER JOIN ConferenceArticle ca ON art.article_ID=ca.article_ID WHERE ca.conference_name=?1")
    List<Article>  findConferenceArticles(String conf_name);

    @Query("SELECT ath FROM Author ath INNER JOIN ConferenceArticle ca ON ath.article_ID=ca.article_ID WHERE ca.conference_name=?1")
    List<Author> findConferenceAuthors(String conf_name);

    @Query("SELECT COUNT(art.article_ID) * 1.0 / COUNT(ca.published_year) FROM Article art INNER JOIN ConferenceArticle ca ON art.article_ID=ca.article_ID WHERE ca.conference_name=?1")
    Double findAvgConferenceArticles(String conf_name);

    // YEAR RANGE QUERIES

    @Query("SELECT COUNT(DISTINCT ath.author_ID) FROM Author ath INNER JOIN ConferenceArticle ca ON ath.article_ID=ca.article_ID WHERE ca.conference_name=?1 AND ca.published_year>=?2 AND ca.published_year<=?3")
    Integer findNumOfConferenceAuthorsRange(String conf_name, int y1, int y2);

    @Query("SELECT COUNT(ath.author_ID) * 1.0 / COUNT(ca.article_ID) FROM Author ath INNER JOIN ConferenceArticle ca ON ath.article_ID=ca.article_ID WHERE ca.conference_name=?1 AND ca.published_year>=?2 AND ca.published_year<=?3")
    Double findAvgAuthorsByConferenceRange(String conf_name, int y1, int y2);

    @Query("SELECT COUNT(ath.author_ID) * 1.0 / COUNT(ca.published_year) FROM Author ath INNER JOIN ConferenceArticle ca ON ath.article_ID=ca.article_ID WHERE ca.conference_name=?1 AND ca.published_year>=?2 AND ca.published_year<=?3")
    Double findAvgAuthorsByYearRange(String conf_name, int y1, int y2);

    @Query("SELECT art FROM Article art INNER JOIN ConferenceArticle ca ON art.article_ID=ca.article_ID WHERE ca.conference_name=?1 AND ca.published_year>=?2 AND ca.published_year<=?3")
    List<Article> findConferenceArticlesRange(String conf_name, int y1, int y2);

    @Query("SELECT ath FROM Author ath INNER JOIN ConferenceArticle ca ON ath.article_ID=ca.article_ID WHERE ca.conference_name=?1 AND ca.published_year>=?2 AND ca.published_year<=?3")
    List<Author> findConferenceAuthorsRange(String conf_name, int y1, int y2);

    @Query("SELECT COUNT(art.article_ID) * 1.0 / COUNT(ca.published_year) FROM Article art INNER JOIN ConferenceArticle ca ON art.article_ID=ca.article_ID WHERE ca.conference_name=?1 AND ca.published_year>=?2 AND ca.published_year<=?3")
    Double findAvgConferenceArticlesRange(String conf_name, int y1, int y2);

    //TODO REPORT

    @Query("SELECT  ca.published_year, COUNT(ca.conference_ID) FROM ConferenceCategory cc INNER JOIN ConferenceRanking cr ON cr.primaryFoR=cc.primaryFoR INNER JOIN ConferenceArticle ca ON ca.conference_ID=cr.conference_ID WHERE cc.title=?1 GROUP BY ca.published_year")
    List<Object[]> findNumOfConferenceByCategory(String categoryName);

    @Query("SELECT ca.published_year, COUNT(ath.author_ID) / COUNT(ca.article_ID), COUNT(DISTINCT ca.article_ID )FROM ConferenceArticle ca INNER JOIN Author ath ON ca.article_ID=ath.article_ID WHERE ca.conference_name=?1 GROUP BY ca.published_year")
    List<Object[]> findAvgAuthorsNumArticlesByYear(String conf_name);
}


