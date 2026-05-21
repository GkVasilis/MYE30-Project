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

        super();
        this.author_ID = author_ID;
        this.author_name = author_name;
        this.article_ID = article_ID;
        this.title = title;
    }


    public int getauthor_ID() {
        return author_ID;
    }


    public void setauthor_ID(int author_ID) {
        this.author_ID = author_ID;
    }


    public String getauthor_name() {
        return author_name;
    }


    public void setauthor_name(String author_name) {
        this.author_name = author_name;
    }


    public int getarticle_ID() {
        return article_ID;
    }


    public void setarticle_ID(int article_ID) {
        this.article_ID = article_ID;
    }




    public String gettitle() {
        return title;
    }


    public void settitle(String title) {
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