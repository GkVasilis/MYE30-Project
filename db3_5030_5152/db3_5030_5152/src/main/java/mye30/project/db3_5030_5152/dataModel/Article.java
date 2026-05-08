package mye30.project.db3_5030_5152.dataModel;

import jakarta.persistence.*;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @Column(name = "article_ID")
    private int article_ID;

    @Column(name = "title")
    private String title;

    @Column(name = "published_year")
    private int published_year;

    public Article() {}

    public Article(int article_ID, String title, int published_year) {
        this.article_ID = article_ID;
        this.title = title;
        this.published_year = published_year;
    }

    public int getArticle_ID() {
        return article_ID;
    }

    public void setArticle_ID(int article_ID) {
        this.article_ID = article_ID;
    }

    public String  getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublished_year() {
        return published_year;
    }

    public void setPublished_year(int published_year) {
        this.published_year = published_year;
    }

    @Override
    public String toString() {
        return "Article{" +
                "article_ID=" + article_ID +
                ", title='" + title + '\'' +
                ", published_year=" + published_year +
                '}';
    }
}
