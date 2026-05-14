package mye30.project.db3_5030_5152.repositories;

import mye30.project.db3_5030_5152.dataModel.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Integer>{

    @Query("SELECT cr FROM Conference c INNER JOIN ConferenceRanking cr ON c.conference_ID=cr.conference_ID WHERE c.conference_ID=?1")
    ConferenceRanking findConferenceRanking(String conf_name);

    @Query("SELECT ca.published_year, COUNT(DISTINCT (art.article_ID)) FROM Article art INNER JOIN ConferenceArticle ca ON art.article_ID=ca.article_ID WHERE ca.conference_name=?1 GROUP BY ca.published_year")
    int findNumOfConferenceArticlesByYear(String conf_name);

    @Query("SELECT ca.published_year, COUNT(ath.author_ID), COUNT(DISTINCT (ath.author_ID)) FROM Author ath INNER JOIN ConferenceArticle ca ON ath.article_ID=ca.article_ID WHERE ca.conference_name=?1 GROUP BY ca.published_year")
    List<Object[]> findNumOfConferenceAuthorsByYear(String author_name);


}
