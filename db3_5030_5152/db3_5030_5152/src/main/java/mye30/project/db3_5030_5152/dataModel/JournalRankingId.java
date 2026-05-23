package mye30.project.db3_5030_5152.dataModel;

import java.io.Serializable;
import java.util.Objects;

public class JournalRankingId implements Serializable {
    private int journal_ID; // Must match JournalRanking field names exactly
    private int rank;
    private String title;

    // Default Constructor
    public JournalRankingId() {}

    public JournalRankingId(int journal_id, int rank, String title) {
        this.journal_ID = journal_id;
        this.rank = rank;
        this.title = title;
    }

    // Required equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JournalRankingId that = (JournalRankingId) o;
        return journal_ID == that.journal_ID &&
                rank == that.rank &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(journal_ID, rank, title);
    }
}