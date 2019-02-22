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
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity(name = "position")
public class Position {
	
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "Position_ID", nullable = false)
    int positionId;

    @Column(name = "Position_Name", unique = true, nullable = false)
    String positionName;
    
//    @Column(mappedBy="positionId")
//    User userId;

    
    public Position() {
    }

    public Position(String positionName) {
        this.setPositionName(positionName);
    }
    
    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int id) {
        this.positionId = id;
    }

    public String getPositionName() {
        return positionName;
    }

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
    
}
