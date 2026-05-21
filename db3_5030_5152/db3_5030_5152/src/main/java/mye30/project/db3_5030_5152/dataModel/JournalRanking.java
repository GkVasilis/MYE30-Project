package mye30.project.db3_5030_5152.dataModel;

import jakarta.persistence.*;

@Entity
@Table(name = "journal_rankings")
@IdClass(JournalRanking.class)
public class JournalRanking {

    @Id
    @Column(name = "journal_ID")
    private int journal_ID;

    @Id
    @Column(name = "j_rank")
    private int rank;

    @Id
    @Column(name = "title")
    private String title;

    @Column(name = "bestSubjectArea")
    private String bestSubjectArea;

    @Column(name = "bestSubjectRank")
    private String bestSubjectRank;

    @Column(name = "country")
    private String country;

    @Column(name = "bestCategories")
    private String bestCategories;

    @Column(name = "journal_language")
    private String journal_language;

    public JournalRanking() {}

    public JournalRanking(int journal_ID, int rank, String title, String bestSubjectArea, String bestSubjectRank, String country, String  bestCategories, String journal_language) {
        this.journal_ID = journal_ID;
        this.rank = rank;
        this.title = title;
        this.bestSubjectArea = bestSubjectArea;
        this.bestSubjectRank = bestSubjectRank;
        this.country = country;
        this.bestCategories = bestCategories;
        this.journal_language = journal_language;
    }

    public int getJournal_ID() {
        return journal_ID;
    }

    public void setJournal_ID(int journal_ID) {
        this.journal_ID = journal_ID;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBestSubjectArea() {
        return bestSubjectArea;
    }

    public void setBestSubjectArea(String bestSubjectArea) {
        this.bestSubjectArea = bestSubjectArea;
    }

    public String getBestSubjectRank() {
        return bestSubjectRank;
    }

    public void setBestSubjectRank(String bestSubjectRank) {
        this.bestSubjectRank = bestSubjectRank;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBestCategories() {
        return bestCategories;
    }

    public void setBestCategories(String bestCategories) {
        this.bestCategories = bestCategories;
    }

    public String getJournal_language() {
        return journal_language;
    }

    public void setJournal_language(String journal_language) {
        this.journal_language = journal_language;
    }

    @Override
    public String toString() {
        return "JournalRanking{" +
                "journal_ID=" + journal_ID +
                ", rank=" + rank +
                ", title='" + title + '\'' +
                ", bestSubjectArea='" + bestSubjectArea + '\'' +
                ", bestSubjectRank='" + bestSubjectRank + '\'' +
                ", country='" + country + '\'' +
                ", bestCategories='" + bestCategories + '\'' +
                ", journal_language='" + journal_language + '\'' +
                '}';
    }
}
