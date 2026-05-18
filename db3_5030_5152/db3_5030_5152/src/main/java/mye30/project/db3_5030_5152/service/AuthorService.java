package mye30.project.db3_5030_5152.service;

import mye30.project.db3_5030_5152.dataModel.*;
import java.util.List;

public interface AuthorService {

    int findLastPublishedYear(String author_name);

    int findFirstPublishedYear(String author_name);

    List<Article> findAllArticles(String author_name);

    List<Object[]> findNumArticlesByYear(String author_name);

    int findAvgArticlesByYear(String author_name);


}
