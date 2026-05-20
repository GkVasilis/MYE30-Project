package mye30.project.db3_5030_5152.serviceImplementation;

import mye30.project.db3_5030_5152.dataModel.*;
import mye30.project.db3_5030_5152.repositories.*;
import mye30.project.db3_5030_5152.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImp implements AuthorService {

    @Autowired
    private AuthorRepositoty repo;


    @Override
    public List<String> findAllAuthors() {
        return repo.findAllAuthors();
    }

    @Override
    public int findLastPublishedYear(String author_name) {
        return repo.findLastPublishedYear(author_name);
    }

    @Override
    public int findFirstPublishedYear(String author_name) {
        return repo.findFirstPublishedYear(author_name);
    }

    @Override
    public List<Article> findAllArticles(String author_name) {
        return repo.findAllArticles(author_name);
    }

    @Override
    public List<Object[]> findNumArticlesByYear(String author_name) {
        return repo.findNumArticlesByYear(author_name);
    }

    @Override
    public int findAvgArticlesByYear(String author_name) {
        return repo.findAvgArticlesByYear(author_name);
    }
}
