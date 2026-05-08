package mye30.project.db3_5030_5152.dataModel;
import jakarta.persistence.*;

@Entity
@Table(name = "Conference")

public class Conference{

    @Id
    @Column(name= "conference_ID")

    private  int conference_ID;


    @Column(name= "conference_name")

    private  String conference_name;



    public Conference() {

    }


    public Conference(int conference_ID, String conference_name) {

        super();
        this.conference_ID = conference_ID;
        this.conference_name = conference_name;

    }


    public int getConference_ID() {
        return conference_ID;
    }

    public void setConference_ID(int conference_ID) {
        this.conference_ID = conference_ID;
    }

    public String getConference_name() {
        return conference_name;
    }

    public void setConference_name(String conference_name) {
        this.conference_name = conference_name;
    }


    @Override
    public String toString() {
        return "Conference{" +
                "conference_ID=" + conference_ID +
                ", conference_name='" + conference_name + '\'' +
                '}';
    }
}
