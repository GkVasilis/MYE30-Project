package mye30.project.db3_5030_5152.service;

import mye30.project.db3_5030_5152.repositories.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface YearService {



    int findPublishedArticles(int year);

    int findNumOfJournals(int year);

    int findNumOfConferences(int year);

    List<Object[]> findNumOfAuthors(int year);

    List<Object[]> findPublicationByJournal(int year, String jName);

    List<Object[]> findPublicationByConference(int year, String cName);

    List<Object[]> findPublicationByAuthor(int year, String aName);
}


