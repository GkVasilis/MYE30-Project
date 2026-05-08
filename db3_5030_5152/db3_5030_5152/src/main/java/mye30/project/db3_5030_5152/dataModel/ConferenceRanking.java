package mye30.project.db3_5030_5152.dataModel;
import jakarta.persistence.*;


@Entity
@Table(name = "ConferenceArticle")
public class ConferenceRanking{

    @Id
    @Column(name = "conf_rank_ID")
    private int conf_rank_ID;

    @Column(name = "conference_ID")
    private int conference_ID;

    @Column(name = "title")
    private String title;

    @Column(name = "rank")
    private String rank;


    @Column(name = "primaryFoR")
    private String primaryFoR;


    public ConferenceRanking(){}

    public ConferenceRanking(int conf_rank_ID,int conference_ID, String title,String rank, String primaryFoR){
        this.conf_rank_ID= conf_rank_ID;
        this.conference_ID= conference_ID;
        this.title = title;
        this.rank =  rank;
        this.primaryFoR = primaryFoR;
    }

    public int getConf_rank_ID() {
        return conf_rank_ID;
    }

    public void setConf_rank_ID(int conf_rank_ID) {
        this.conf_rank_ID = conf_rank_ID;
    }

    public int getConference_ID() {
        return conference_ID;
    }

    public void setConference_ID(int conference_ID) {
        this.conference_ID = conference_ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPrimaryFoR() {
        return primaryFoR;
    }

    public void setPrimaryFoR(String primaryFoR) {
        this.primaryFoR = primaryFoR;
    }


    @Override
    public String toString() {
        return "ConferenceRanking{" +
                "conf_rank_ID=" + conf_rank_ID +
                ", conference_ID=" + conference_ID +
                ", title='" + title + '\'' +
                ", rank='" + rank + '\'' +
                ", primaryFoR='" + primaryFoR + '\'' +
                '}';
    }
}
