package mye30.project.db3_5030_5152.serviceImplementation;

import mye30.project.db3_5030_5152.dataModel.*;
import mye30.project.db3_5030_5152.repositories.*;
import mye30.project.db3_5030_5152.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalServiceImp implements JournalService {

    @Autowired
    private JournalRepository repo;

    @Override
    public JournalRanking findJournalRanking(String journal_name) {
        return repo.findJournalRanking(journal_name);
    }

    @Override
    public List<Object[]> findNumOfJournalArticlesByYear(String journal_name){
        return repo.findNumOfJournalArticlesByYear(journal_name);
    }

    @Override
    public List<Object[]> findNumOfJournalAuthorsByYear(String author_name) {
        return repo.findNumOfJournalAuthorsByYear(author_name);
    }

    @Override
    public List<Object[]> findJournalByLastYear(String journal_name) {
        return repo.findJournalByLastYear(journal_name);
    }

    @Override
    public List<Object[]> findJournalByFirstYear(String journal_name) {
        return repo.findJournalByFirstYear(journal_name);
    }


    @Override
    public int findNumOfJournalAuthors(String journal_name){
        return repo.findNumOfJournalAuthors(journal_name);
    }


    @Override
    public int findAvgAuthorsByJournal(String journal_name){
        return repo.findAvgAuthorsByJournal(journal_name);
    }

    public int findAvgAuthorsByYear(String journal_name){

        return repo.findAvgAuthorsByYear(journal_name);
    }

    public  List<Article> findJournalArticles(String journal_name){
        return repo.findJournalArticles(journal_name);
    }

    public List<Author> findJournalAuthors(String journal_name){
        return repo.findJournalAuthors(journal_name);

    }

    public int findAvgJournalArticles(String journal_name){

        return repo.findAvgJournalArticles(journal_name);
    }

    public int findNumOfJournalAuthorsRange(String journal_name, int y1, int y2){

        return repo.findNumOfJournalAuthorsRange(journal_name,y1,y2);
    }

    public int findAvgAuthorsByJournalRange(String journal_name, int y1, int y2){

        return repo.findAvgAuthorsByJournalRange(journal_name,y1,y2);
    }

    @Override
    public int findAvgAuthorsByYearRange(String journal_name, int y1, int y2) {
        return repo.findAvgAuthorsByYearRange(journal_name, y1, y2);
    }

    @Override
    public List<Article> findJournalArticlesRange(String journal_name, int y1, int y2) {
        return repo.findJournalArticlesRange(journal_name, y1, y2);
    }

    @Override
    public List<Author> findJournalAuthorsRange(String journal_name, int y1, int y2) {
        return repo.findJournalAuthorsRange(journal_name, y1, y2);
    }

    @Override
    public int findAvgJournalArticlesRange(String journal_name, int y1, int y2) {
        return repo.findAvgJournalArticlesRange(journal_name, y1, y2);
    }

    // TODO REPORT

    @Override
    public List<Object[]> findNumOfJournalByCategory(String categoryName) {
        return repo.findNumOfJournalByCategory(categoryName);
    }

    @Override
    public int findPublisherPublications(String publisher) {
        return repo.findPublisherPublications(publisher);
    }

    @Override
    public List<Object[]> findAvgAuthorsNumArticlesByYear(String journal_name) {
        return repo.findAvgAuthorsNumArticlesByYear(journal_name);
    }
}
