package mye30.project.db3_5030_5152.dataModel;
import jakarta.persistence.*;

@Entity
@Table(name="authors")
@IdClass(AuthorId.class)
public class Author {

    @Id
    @Column(name = "author_ID")
    private int author_ID;

    @Id
    @Column(name = "author_name")
    private String author_name;

    @Id
    @Column(name = "article_ID")
    private int article_ID;

    @Column(name = "title")
    private String title;

    public Author() {
    }

    public Author(int author_ID, String author_name, int article_ID, String title) {
        this.author_ID = author_ID;
        this.author_name = author_name;
        this.article_ID = article_ID;
        this.title = title;
    }

    public int getAuthor_ID() {
        return author_ID;
    }

    public void setAuthor_ID(int author_ID) {
        this.author_ID = author_ID;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
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

    @Override
    public String toString() {
        return "Author{" +
                "author_ID=" + author_ID +
                ", author_name='" + author_name + '\'' +
                ", article_ID=" + article_ID +
                ", title='" + title + '\'' +
                '}';
    }
}