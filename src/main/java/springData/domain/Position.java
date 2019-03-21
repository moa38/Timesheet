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
 * Position class representing a position held by a user. Has List<appUsers>
 *
 * @author CO2015 Group-17
 */
@Entity
public class Position {

   @Column(unique = true, updatable = false, nullable = false)
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int positionId;

   @Column
   private String positionName;

   @OneToMany(mappedBy = "position")
   private List<User> users;

   public Position(int positionId, String positionName) {
      this.positionId = positionId;
      this.positionName = positionName;
   }

   public Position() {
   }

   public int getPositionId() {
      return this.positionId;
   }

   public void setPositionId(int positionId) {
      this.positionId = positionId;
   }

   public String getPositionName() {
      return this.positionName;
   }

   public void setPositionName(String positionName) {
      this.positionName = positionName;
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
      user.setPosition(this);
   }

   public void removeUser(User user) {
      getUsers().remove(user);
      user.setPosition(null);
   }

}

