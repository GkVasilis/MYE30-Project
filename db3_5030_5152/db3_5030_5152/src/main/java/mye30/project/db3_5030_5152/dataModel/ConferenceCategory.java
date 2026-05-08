package mye30.project.db3_5030_5152.dataModel;

import jakarta.persistence.*;

@Entity
@Table(name = "conference_categories")
public class ConferenceCategory {

    /* TODO id???? */
    @Column(name = "primaryFor")
    private String primaryFor;

    @Id
    @Column(name = "title")
    private String title;

    public ConferenceCategory() {}

    public ConferenceCategory(String primaryFor, String title) {
        this.primaryFor = primaryFor;
        this.title = title;
    }

    public String getPrimaryFor() {
        return primaryFor;
    }

    public void setPrimaryFor(String primaryFor) {
        this.primaryFor = primaryFor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ConferenceCategory{" +
                "primaryFor='" + primaryFor + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

}
