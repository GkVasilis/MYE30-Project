package mye30.project.db3_5030_5152.dataModel;
import jakarta.persistence.*;

@Entity
@Table(name = "conference_articles")
public class ConferenceArticle {

    @Id
    @Column(name = "conference_ID")
    private int conference_ID;

    @Column(name = "article_ID")
    private int article_ID;

    @Column(name = "conference_name")
    private String conference_name;

    @Column(name = "title")
    private String title;

    @Column(name = "cdrom")
    private String cdrom;

    @Column(name = "crossref")
    private String crossref;

    @Column(name = "publtype")
    private String publtype;

    @Column(name = "url")
    private String url;

    @Column(name = "pages")
    private String pages;

    @Column(name = "mdate")
    private String mdate;

    @Column(name = "published_year")
    private int published_year;

    @Column(name = "conference_key")
    private String conference_key;


    public ConferenceArticle(){}

    public ConferenceArticle(int conference_ID,int article_ID,String conference_name,String title,String cdrom,String crossref,String publtype,String url,String pages,String mdate, int published_year,String conference_key){
        this.conference_ID = conference_ID;
        this.article_ID= article_ID;
        this.conference_name = conference_name;
        this.title = title;
        this.cdrom = cdrom;
        this.crossref = crossref;
        this.publtype = publtype;
        this.url = url;
        this.pages = pages;
        this.mdate = mdate;
        this.published_year = published_year;
        this.conference_key = conference_key;

    }

    public int getConference_ID() {
        return conference_ID;
    }

    public void setConference_ID(int conference_ID) {
        this.conference_ID = conference_ID;
    }

    public int getArticle_ID() {
        return article_ID;
    }

    public void setArticle_ID(int article_ID) {
        this.article_ID = article_ID;
    }

    public String getConference_name() {
        return conference_name;
    }

    public void setConference_name(String conference_name) {
        this.conference_name = conference_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCdrom() {
        return cdrom;
    }

    public void setCdrom(String cdrom) {
        this.cdrom = cdrom;
    }

    public String getCrossref() {
        return crossref;
    }

    public void setCrossref(String crossref) {
        this.crossref = crossref;
    }

    public String getPubltype() {
        return publtype;
    }

    public void setPubltype(String publtype) {
        this.publtype = publtype;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public int getPublished_year() {
        return published_year;
    }

    public void setPublished_year(int published_year) {
        this.published_year = published_year;
    }

    public String getConference_key() {
        return conference_key;
    }

    public void setConference_key(String conference_key) {
        this.conference_key = conference_key;
    }

    @Override
    public String toString() {
        return "ConferenceArticle{" +
                "conference_ID=" + conference_ID +
                ", article_ID=" + article_ID +
                ", conference_name='" + conference_name + '\'' +
                ", title='" + title + '\'' +
                ", cdrom='" + cdrom + '\'' +
                ", crossref='" + crossref + '\'' +
                ", publtype='" + publtype + '\'' +
                ", url='" + url + '\'' +
                ", pages='" + pages + '\'' +
                ", mdate='" + mdate + '\'' +
                ", published_year=" + published_year +
                ", conference_key='" + conference_key + '\'' +
                '}';
    }
}


