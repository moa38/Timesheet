package springData;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import springData.domain.Position;
import springData.domain.User;

public class PositionTest {
   Position position;

   @Before
   public void setUpPosition() {
      //Create new Position to be tested
      position = new Position(995, "tester");
   }

   @Test
   public void positionHasId() {
      //Checks that position has positionId property
      assertThat(position, Matchers.hasProperty("positionId"));
   }

   @Test
   public void positionHasName() {
      //Checks that position has given name
      assertThat(position, Matchers.hasProperty("positionName", Matchers.equalTo("tester")));
   }

   @Test
   public void positionHasNewName() {
      //Checks that position has the new name and it is correct
      position.setPositionName("newName");
      assertThat(position, Matchers.hasProperty("positionName", Matchers.not(Matchers.equalTo("positionName"))));
      assertThat(position, Matchers.hasProperty("positionName", Matchers.equalTo("newName")));
   }

   @Test
   public void positionHasUserList() {
      //Checks that position has a populated list of users

      //Create list and users
      ArrayList<User> userList = new ArrayList<User>();
      User u1 = new User("John", "Smith", "abcd");
      User u2 = new User();

      //Populate list with users
      position.setUsers(userList);
      position.addUser(u1);
      position.addUser(u2);

      //Check that list has the correct size(2)
      assertThat(position.getUsers(), Matchers.hasSize(2));

      //Remove u2 and check if list has correct size(1)
      position.removeUser(u2);
      assertThat(position.getUsers(), Matchers.hasSize(1));
   }

}

