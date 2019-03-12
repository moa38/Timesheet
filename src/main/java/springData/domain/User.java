package springData.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author CO2015 Group-17
 */
@Entity(name = "User")
@Table(name = "User")
@NamedQuery(name = "User.findAll", query = "Select e from User e")
public class User {

   @Column(unique = true, updatable = false, nullable = false)
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @NotNull
   private int userId;

   @Basic
   private String firstName;

   @Basic
   private String lastName;

   @Column(nullable = false)
   @Basic(optional = false)
   @NotNull(message = "Password can not be empty")
   private String password;

   @ManyToOne
   private Position position;

   @ManyToOne
   private Organization organization;

   @OneToMany(mappedBy = "user")
   private List<Timesheet> timesheets;

   public User() {
   }

   public User(String firstName, String lastName, String password) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.password = password;
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
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Position getPosition() {
      return this.position;
   }

   public void setPosition(Position position) {
      this.position = position;
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

}

