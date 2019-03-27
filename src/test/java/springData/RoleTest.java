package springData;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import springData.domain.Role;
import springData.domain.User;

public class RoleTest {
   Role role;

   @Before
   public void setUpRole() {
      //Create new Role to be tested
      role = new Role(995, "tester");
   }

   @Test
   public void roleHasId() {
      //Checks that role has roleId property
      assertThat(role, Matchers.hasProperty("id", Matchers.is(995)));
   }

   @Test
   public void roleHasName() {
      //Checks that role has given name
      assertThat(role, Matchers.hasProperty("role", Matchers.equalTo("tester")));
   }

   @Test
   public void roleHasNewName() {
      //Checks that role has the new name and it is correct
      role.setRole("newName");
      assertThat(role, Matchers.hasProperty("role", Matchers.not(Matchers.equalTo("role"))));
      assertThat(role, Matchers.hasProperty("role", Matchers.equalTo("newName")));
   }

   @Test
   public void roleHasUserSet() {
      //Checks that role has a set of users

      //Create users
      User u1 = new User("John", "Smith", "abcd");
      User u2 = new User();

      //Populate set with users
      role.getUserRoles().add(u1);
      role.getUserRoles().add(u2);

      //Check that set has the correct size(2)
      assertThat(role.getUserRoles(), Matchers.hasSize(2));

      //Remove u2 and check that set has correct size(1)
      role.getUserRoles().remove(u2);
      assertThat(role.getUserRoles(), Matchers.hasSize(1));
   }

}

