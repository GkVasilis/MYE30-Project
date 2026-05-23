package mye30.project.db3_5030_5152.service;

import mye30.project.db3_5030_5152.dataModel.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorService {

    //List<String> findAllAuthors();
    Page<String> findAllAuthors(Pageable pageable);

    Integer findLastPublishedYear(String author_name);

    Integer findFirstPublishedYear(String author_name);

    List<Article> findAllArticles(String author_name);

    List<Object[]> findNumArticlesByYear(String author_name);

    Double findAvgArticlesByYear(String author_name);

}
