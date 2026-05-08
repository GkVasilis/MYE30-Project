package mye30.project.db3_5030_5152.dataModel;

import jakarta.persistence.*;

@Entity
@Table(name = "journals")
public class Journal {

    @Id
    @Column(name = "journal_ID")
    private int journal_ID;

    @Column(name = "journal_name")
    private String journal_name;

    @Column(name = "publisher")
    private String publisher;

    public Journal() {}

    public Journal(int journal_ID, String journal_name, String publisher) {
        this.journal_ID = journal_ID;
        this.journal_name = journal_name;
        this.publisher = publisher;
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
}
