package springData.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Organization class representing the Organization appUsers work for. Has List<appUsers>
 *
 * @author CO2015 Group-17
 */
@Entity
public class Organization {

   @Column(unique = true, updatable = false, nullable = false)
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int organizationId;

   @Column
   private String name;

   @Column
   private String address;

   @Column
   private String contactNumber;

   @OneToMany(mappedBy = "organization")
   private List<User> users;

   public Organization(String name, String address, String contactNumber) {
      this.name = name;
      this.address = address;
      this.contactNumber = contactNumber;
   }

   public Organization() {
   }

   public int getOrganizationId() {
      return this.organizationId;
   }

   public void setOrganizationId(int organizationId) {
      this.organizationId = organizationId;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAddress() {
      return this.address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getContactNumber() {
      return this.contactNumber;
   }

   public void setContactNumber(String contactNumber) {
      this.contactNumber = contactNumber;
   }

   public List<User> getUsers() {
      if (users == null) {
         users = new ArrayList<>();
      }
      return this.users;
   }

   public void setUsers(List<User> users) {
      this.users = users;
   }

   public void addUser(User user) {
      getUsers().add(user);
      user.setOrganization(this);
   }

   public void removeUser(User user) {
      getUsers().remove(user);
      user.setOrganization(null);
   }
}
