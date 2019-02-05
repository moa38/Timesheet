package springData.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity(name = "position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "Position ID", nullable = false)
    int positionid;

    @Column(name = "Position Name", unique = true, nullable = false)
    String positionname;

    public Position() {
    }

    public Position(String positionname) {
        this.setPositionName(positionname);
    }

    public int getPositionId() {
        return positionid;
    }

    public void setPositionId(int id) {
        this.positionid = id;
    }

    public String getPositionName() {
        return positionname;
    }

    public void setPositionName(String positionname) {
        this.positionname = positionname;
    }
}
