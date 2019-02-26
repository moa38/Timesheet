package springData.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class User {

   @Column(unique = true, updatable = false, nullable = false)
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int userId;

   @Basic
   private String firstName;

   @Basic
   private String lastName;

   @Column(nullable = false)
   @Basic(optional = false)
   @NotNull(message = "Password can not be empty")
   private String password;

   @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
   public List<Shift> shifts;

   public User() {
   }

   public User(int userId, String firstName, String lastName, String password) {
      this.userId = userId;
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

   public List<Shift> getShifts() {
      if (shifts == null) {
         shifts = new ArrayList<>();
      }
      return this.shifts;
   }

   public void setShifts(List<Shift> shifts) {
      this.shifts = shifts;
   }

   public void addShift(Shift shift) {
      getShifts().add(shift);
   }

   public void removeShift(Shift shift) {
      getShifts().remove(shift);
   }

}
