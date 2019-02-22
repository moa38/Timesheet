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
    @Column(name = "Organization_ID", nullable = false)
    int organizationId;

    @Column(name = "Name", unique = true, nullable = false)
    String name;

    @Column(name = "Address", unique = true, nullable = false)
    String address;

    @Column(name = "Contact_Number", unique = true, nullable = false)
    String contactNumber;
    
    @OneToMany(mappedBy="organizationId")
    private List<User> users;

    public Organization() {
    }

    public Organization(int organizationId, String name, String address, String contactNumber) {
        this.setOrganizationId(organizationId);
    	this.setName(name);
        this.setAddress(address);
        this.contactNumber = contactNumber;
    }

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
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

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

    

}
