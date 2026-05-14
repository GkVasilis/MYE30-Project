package mye30.project.db3_5030_5152.repositories;

import mye30.project.db3_5030_5152.dataModel.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepositoty extends JpaRepository<Author, Integer> {

    @Query("SELECT MAX(art.published_year) FROM Author ath INNER JOIN Article art ON ath.article_ID = art.article_ID WHERE ath.author_name=?1")
    int findLastPublishedYear(String author_name);

    @Query("SELECT MIN(art.published_year) FROM Author ath INNER JOIN Article art ON ath.article_ID = art.article_ID WHERE ath.author_name=?1")
    int findFirstPublishedYear(String author_name);

    @Query("SELECT art FROM Author ath INNER JOIN Article art ON ath.article_ID = art.article_ID WHERE ath.author_name=?1")
    List<Article> findAllArticles(String author_name);

    @Query("SELECT art.published_year , COUNT (DISTINCT (art.article_ID)) FROM Author ath INNER JOIN Article art ON ath.article_ID = art.article_ID WHERE ath.author_name=?1 GROUP BY art.published_year")
    List<Object[]> findNumArticlesByYear(String author_name);

    @Query("SELECT COUNT (art) / (MAX(art.published_year) - MIN(art.published_year)+1) FROM Author ath INNER JOIN Article art ON ath.article_ID = art.article_ID WHERE ath.author_name=?1")
    int findAvgArticlesByYear(String author_name);
}




