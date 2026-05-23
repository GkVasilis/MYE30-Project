package mye30.project.db3_5030_5152.serviceImplementation;

import mye30.project.db3_5030_5152.dataModel.*;
import mye30.project.db3_5030_5152.repositories.*;
import mye30.project.db3_5030_5152.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.deser.CreatorProperty;

import java.security.PublicKey;
import java.util.List;


@Service
public class ConferenceServiceImp implements ConferenceService {

    @Autowired
    private ConferenceRepository repo;

    @Override
    public List<String> findAllConferences() {
        return repo.findAllConferences();
    }

    @Override
    public ConferenceRanking findConferenceRanking(String conf_name){
        return repo.findConferenceRanking(conf_name);
    }

    @Override
    public List<Object[]> findNumOfConferenceArticlesByYear(String conf_name){
        return repo.findNumOfConferenceArticlesByYear(conf_name);
    }

    @Override
    public List<Object[]> findNumOfConferenceAuthorsByYear(String author_name){
        return repo.findNumOfConferenceAuthorsByYear(author_name);
    }

    @Override
    public Integer findConferenceByLastYear(String conf_name){
        return repo.findConferenceByLastYear(conf_name);
    }

    @Override
    public Integer findConferenceByFirstYear(String conf_name){
        return repo.findConferenceByFirstYear(conf_name);
    }

    @Override
    public Integer findNumOfConferenceAuthors(String conf_name){
        return repo.findNumOfConferenceAuthors(conf_name);
    }

    @Override
    public Double findAvgAuthorsByConference(String conf_name){
        return repo.findAvgAuthorsByConference(conf_name);
    }

    @Override
    public Double findAvgAuthorsByYear(String conf_name) {
        return repo.findAvgAuthorsByYear(conf_name);
    }

    @Override
    public List<Article>  findConferenceArticles(String conf_name) {
        return repo.findConferenceArticles(conf_name);
    }

    @Override
    public List<Author> findConferenceAuthors(String conf_name) {
        return repo.findConferenceAuthors(conf_name);
    }

    @Override
    public Double findAvgConferenceArticles(String conf_name) {
        return repo.findAvgConferenceArticles(conf_name);
    }

    @Override
    public Integer findNumOfConferenceAuthorsRange(String conf_name, int y1, int y2) {
        return repo.findNumOfConferenceAuthorsRange(conf_name, y1, y2);
    }

    @Override
    public Double findAvgAuthorsByConferenceRange(String conf_name, int y1, int y2) {
        return repo.findAvgAuthorsByConferenceRange(conf_name, y1, y2);
    }

    @Override
    public Double findAvgAuthorsByYearRange(String conf_name, int y1, int y2) {
        return repo.findAvgAuthorsByYearRange(conf_name, y1, y2);
    }

    @Override
    public List<Article> findConferenceArticlesRange(String conf_name, int y1, int y2) {
        return repo.findConferenceArticlesRange(conf_name, y1, y2);
    }

    @Override
    public List<Author> findConferenceAuthorsRange(String conf_name, int y1, int y2) {
        return repo.findConferenceAuthorsRange(conf_name, y1, y2);
    }

    @Override
    public Double findAvgConferenceArticlesRange(String conf_name, int y1, int y2) {
        return repo.findAvgConferenceArticlesRange(conf_name, y1, y2);
    }

    // TODO REPORT

    @Override
    public List<Object[]> findNumOfConferenceByCategory(String categoryName) {
        return repo.findNumOfConferenceByCategory(categoryName);
    }

    @Override
    public List<Object[]> findAvgAuthorsNumArticlesByYear(String conf_name) {
        return repo.findAvgAuthorsNumArticlesByYear(conf_name);
    }
}
