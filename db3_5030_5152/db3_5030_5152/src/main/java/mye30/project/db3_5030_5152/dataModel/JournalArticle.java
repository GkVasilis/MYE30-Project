package mye30.project.db3_5030_5152.dataModel;

import jakarta.persistence.*;

@Entity
@Table(name = "journal_articles")
public class JournalArticle {

    @Column(name = "article_ID")
    private int article_ID;

    @Column(name = "title")
    private String title;

    @Id
    @Column(name = "journal_ID")
    private int journal_ID;

    @Column(name = "journal_name")
    private String journal_name;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "cdrom")
    private String cdrom;

    @Column(name = "crossref")
    private String crossref;

    @Column(name = "mdate")
    private String mdate; //TODO int or date

    @Column(name = "published_year")
    private int published_year;

    @Column(name = "url")
    private String url;

    @Column(name = "pages")
    private String pages;

    @Column(name = "publtype")
    private String publtype;

    @Column(name = "journal_key")
    private String journal_key;

    public JournalArticle() {}

    public JournalArticle(int article_ID, String title, int journal_ID, String journal_name, String publisher, String cdrom, String crossref, String mdate, int published_year, String url, String pages, String publtype, String journal_key) {
        this.article_ID = article_ID;
        this.title = title;
        this.journal_ID = journal_ID;
        this.journal_name = journal_name;
        this.publisher = publisher;
        this.cdrom = cdrom;
        this.crossref = crossref;
        this.mdate = mdate;
        this.published_year = published_year;
        this.url = url;
        this.pages = pages;
        this.publtype = publtype;
        this.journal_key = journal_key;
    }

    public int getArticle_ID() {
        return article_ID;
    }

    public void setArticle_ID(int article_ID) {
        this.article_ID = article_ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getJournal_ID() {
        return journal_ID;
    }

    public void setJournal_ID(int journal_ID) {
        this.journal_ID = journal_ID;
    }

    public String getJournal_name() {
        return journal_name;
    }

    public void setJournal_name(String journal_name) {
        this.journal_name = journal_name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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

    public String getPubltype() {
        return publtype;
    }

    public void setPubltype(String publtype) {
        this.publtype = publtype;
    }

    public String getJournal_key() {
        return journal_key;
    }

    public void setJournal_key(String journal_key) {
        this.journal_key = journal_key;
    }

    @Override
    public String toString() {
        return "JournalArticle{" +
                "article_ID=" + article_ID +
                ", title='" + title + '\'' +
                ", journal_ID=" + journal_ID +
                ", journal_name='" + journal_name + '\'' +
                ", publisher='" + publisher + '\'' +
                ", cdrom='" + cdrom + '\'' +
                ", crossref='" + crossref + '\'' +
                ", mdate='" + mdate + '\'' +
                ", published_year=" + published_year +
                ", url='" + url + '\'' +
                ", pages='" + pages + '\'' +
                ", publtype='" + publtype + '\'' +
                ", journal_key='" + journal_key + '\'' +
                '}';
    }
}
