package mye30.project.db3_5030_5152.dataModel;

import jakarta.persistence.*;

@Entity
@Table(name = "conference_categories")
public class ConferenceCategory {

    /* TODO id???? */
    @Column(name = "primaryFoR")
    private String primaryFoR;

    @Id
    @Column(name = "title")
    private String title;

    public ConferenceCategory() {}

    public ConferenceCategory(String primaryFor, String title) {
        this.primaryFoR = primaryFor;
        this.title = title;
    }

    public String getPrimaryFor() {
        return primaryFoR;
    }

    public void setPrimaryFor(String primaryFor) {
        this.primaryFoR = primaryFoR;
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
                "primaryFor='" + primaryFoR + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

}
