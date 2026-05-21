package mye30.project.db3_5030_5152.dataModel;

import java.io.Serializable;
import java.util.Objects;

public class AuthorId implements Serializable {
    private int article_ID; // Must match Author field names exactly
    private int author_ID;
    private String author_name;

    // Default Constructor
    public AuthorId() {}

    public AuthorId(int article_ID, int author_ID, String author_name) {
        this.article_ID = article_ID;
        this.author_ID = author_ID;
        this.author_name = author_name;
    }

    // MANDATORY: Must override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorId authorId = (AuthorId) o;
        return article_ID == authorId.article_ID &&
                author_ID == authorId.author_ID &&
                Objects.equals(author_name, authorId.author_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(article_ID, author_ID, author_name);
    }
}