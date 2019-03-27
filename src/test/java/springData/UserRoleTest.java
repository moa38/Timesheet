package springData;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import springData.domain.UserRole;
import springData.domain.Role;
import springData.domain.User;

public class UserRoleTest {
   UserRole ur;

   @Before
   public void setUpUserRole() {
      //Create new UserRole to be tested
      ur = new UserRole();
      ur.setId(101);
      Role role = new Role(01, "ROLE_ADMIN");
      User user = new User();
      user.setFirstName("John");
      //user.setRole(role);
      ur.setAppRole(role);
      ur.setAppUser(user);
   }

   @Test
   public void userRoleHasId() {
      //Checks that UserRole has Id property and it is correct
      assertThat(ur, Matchers.hasProperty("id", Matchers.is(101)));
      assertThat(ur, Matchers.hasProperty("id", Matchers.not(Matchers.is(10))));
      //Check that UserRole has updated Id
      ur.setId(76);
      assertThat(ur, Matchers.hasProperty("id", Matchers.is(76)));
   }

   @Test
   public void userRoleHasAppRole() {
      //Checks that UserRole has appRole property and is the correct type
      assertThat(ur, Matchers.hasProperty("appRole", Matchers.isA(Role.class)));
   }

   @Test
   public void userRoleHasAppUser() {
      //Checks that UserRole has appUser property and is the correct type
      assertThat(ur, Matchers.hasProperty("appUser", Matchers.isA(User.class)));
   }

}

