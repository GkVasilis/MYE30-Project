package mye30.project.db3_5030_5152.serviceImplementation;

import mye30.project.db3_5030_5152.dataModel.*;
import mye30.project.db3_5030_5152.repositories.*;
import mye30.project.db3_5030_5152.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.deser.CreatorProperty;

import java.util.List;

@Service
public class YearServiceImp implements YearService {

    @Autowired
    private YearRepository repo;

    @Override
    public List<Integer> findAllYears() {
        return repo.findAllYears();
    }

    @Override
    public int findPublishedArticles(int year){
        return repo.findPublishedArticles(year);
    }

    @Override
    public int findNumOfJournals(int year){
        return repo.findNumOfJournals(year);
    }

    @Override
    public int findNumOfConferences(int year){
        return repo.findNumOfConferences(year);
    }

    @Override
    public List<Object[]> findNumOfAuthors(int year){

        return repo.findNumOfAuthors(year);
    }

    @Override
    public List<Object[]> findPublicationByJournal(int year, String jName){
        return  repo.findPublicationByJournal(year,jName);
    }

    @Override
    public List<Object[]> findPublicationByConference(int year, String cName){
        return repo.findPublicationByConference(year,cName);
    }

    @Override
    public List<Object[]> findPublicationByAuthor(int year, String aName){
        return repo.findPublicationByAuthor(year,aName);
    }

}

