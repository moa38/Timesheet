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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="User_ID", nullable = false)
    int userId;

    @Column(name="Password", unique = true, nullable = false)
    String password;

    @Column(name="First_Name", unique = true, nullable = false)
    String fName;

    @Column(name="Last_Name", unique = true, nullable = false)
    String lName;

    @ManyToOne
    @JoinColumn(name="Organization_ID",nullable = true)
    Organization organizationId;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="Position_ID")
    Position position;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Shifts> shifts;

    public User(){}
    
    public User(String password, String fName, String lName){
//        this.setUserName(username);
        this.setPassword(password);
        this.setFName(fName);
        this.setLName(lName);
    }

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFName() {
		return fName;
	}

	public void setFname(String fName) {
		this.fname = fName;
	}

	public String getLName() {
		return lName;
	}

	public void setLname(String lName) {
		this.lName = lName;
	}

	public Organization getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Organization organizationId) {
		this.organizationId = organizationId;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public List<Shifts> getShifts() {
		return shifts;
	}

	public void setShifts(List<Shifts> shifts) {
		this.shifts = shifts;
	}


    

}