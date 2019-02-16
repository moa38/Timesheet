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
    @Column(name="User ID", nullable = false)
    int userid;

    @Column(name="Password", unique = true, nullable = false)
    String password;

    @Column(name="First Name", unique = true, nullable = false)
    String fname;

    @Column(name="Last Name", unique = true, nullable = false)
    String lname;

    @ManyToOne
    @JoinColumn(name="Organization ID",nullable = true)
    Organization organizationid;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="Position ID")
    Position position;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Shifts> shifts;

    public User(){}
    
    public User(String password, String fname, String lname){
//        this.setUserName(username);
        this.setPassword(password);
        this.setFname(fname);
        this.setLname(lname);
    }

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public Organization getOrganizationid() {
		return organizationid;
	}

	public void setOrganizationid(Organization organizationid) {
		this.organizationid = organizationid;
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