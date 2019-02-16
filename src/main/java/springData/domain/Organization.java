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

@Entity(name = "organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "Organization ID", nullable = false)
    int organizationid;

    @Column(name = "Name", unique = true, nullable = false)
    String name;

    @Column(name = "Address", unique = true, nullable = false)
    String address;

    @Column(name = "Contact Number", unique = true, nullable = false)
    String contactnumber;
    
    @OneToMany(mappedBy="organizationid")
    private List<User> users;

    public Organization() {
    }

    public Organization(String name, String address, String contactnumber) {
        this.setName(name);
        this.setAddress(address);
        this.contactnumber = contactnumber;
    }

	public int getOrganizationid() {
		return organizationid;
	}

	public void setOrganizationid(int organizationid) {
		this.organizationid = organizationid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

    

}
