package mye30.project.db3_5030_5152.service;

import mye30.project.db3_5030_5152.dataModel.*;
import java.util.List;

public interface ConferenceService {

    List<String> findAllConferences();

    ConferenceRanking findConferenceRanking(String conf_name);

    List<Object[]> findNumOfConferenceArticlesByYear(String conf_name);

    List<Object[]> findNumOfConferenceAuthorsByYear(String author_name);

    List<Object[]> findConferenceByLastYear(String conf_name);

    List<Object[]> findConferenceByFirstYear(String conf_name);

    Integer findNumOfConferenceAuthors(String conf_name);

    Double findAvgAuthorsByConference(String conf_name);

    Double findAvgAuthorsByYear(String conf_name);

    List<Article>  findConferenceArticles(String conf_name);

    List<Author> findConferenceAuthors(String conf_name);

    Double findAvgConferenceArticles(String conf_name);

    Integer findNumOfConferenceAuthorsRange(String conf_name, int y1, int y2);

    Double findAvgAuthorsByConferenceRange(String conf_name, int y1, int y2);

    Double findAvgAuthorsByYearRange(String conf_name, int y1, int y2);

    List<Article> findConferenceArticlesRange(String conf_name, int y1, int y2);

    List<Author> findConferenceAuthorsRange(String conf_name, int y1, int y2);

    Double findAvgConferenceArticlesRange(String conf_name, int y1, int y2);

    // TODO REPORT

    List<Object[]> findNumOfConferenceByCategory(String categoryName);

    List<Object[]> findAvgAuthorsNumArticlesByYear(String conf_name);

}


