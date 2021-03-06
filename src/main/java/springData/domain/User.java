package springData.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
//import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(name = "USER_UK", columnNames = "username") })
public class User {

   @Column(name = "USER_ID", updatable = false, nullable = false)
   @Id
   @GeneratedValue
   private int userId;

   @Column
   private String firstName;

   @Column
   private String lastName;

   @Column
   private boolean enabled = true;

   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
   private Role role;

   @Column(unique = true)
   //@Pattern(regexp = "^[a-zA-Z0-9_!#$%&�*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
   private String username;

   @ManyToOne
   private Organization organization;

   @OneToMany(mappedBy = "user")//, cascade = {CascadeType.ALL})
   private List<Timesheet> timesheets;

   @Column(name = "password", length = 128, nullable = false)
   private String password;

   public User() {
   }

   public User(String firstName, String lastName, String username) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.username = username;
   }

   public int getUserId() {
      return this.userId;
   }

   public void setUserId(int userId) {
      this.userId = userId;
   }

   public String getFirstName() {
      return this.firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return this.lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String encryptedPassword) {
      this.password = encryptedPassword;
   }

   public boolean isEnabled() {
      return enabled;
   }

   public void setEnabled(boolean enabled) {
      this.enabled = enabled;
   }

   public Role getRole() {
      return this.role;
   }

   public void setRole(Role role) {
      this.role = role;
   }

   public Organization getOrganization() {
      return this.organization;
   }

   public void setOrganization(Organization organization) {
      this.organization = organization;
   }

   public List<Timesheet> getTimesheets() {
      if (timesheets == null) {
         timesheets = new ArrayList<>();
      }
      return this.timesheets;
   }

   public void setTimesheets(List<Timesheet> timesheets) {
      this.timesheets = timesheets;
   }

   public void addTimesheet(Timesheet timesheet) {
      getTimesheets().add(timesheet);
      timesheet.setUser(this);
   }

   public void removeTimesheet(Timesheet timesheet) {
      getTimesheets().remove(timesheet);
      timesheet.setUser(null);
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

}

