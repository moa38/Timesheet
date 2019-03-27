package springData;

import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import springData.domain.User;
import springData.domain.Organization;
import springData.domain.Role;
import springData.domain.Shift;
import springData.domain.Timesheet;

public class UserTest {
   User user;

   @Before
   public void setUpUser() {
      //Create new AppUser to be tested
      user = new User("John", "Smith", "abcde");
   }

   @Test
   public void userHasId() {
      //Checks that user has userId property
      user.setUserId(003);
      assertThat(user, Matchers.hasProperty("userId", Matchers.equalTo(003)));
   }

   @Test
   public void userHasFirstLastName() {
      //Checks that user has first & last name properties
      assertThat(user, Matchers.hasProperty("firstName"));
      assertThat(user, Matchers.hasProperty("lastName", Matchers.equalTo("Smith")));
   }

   @Test
   public void userHasRole() {
      //Checks that user has role and it is correct
      Role pos = new Role(002, "Admin");
      user.setRole(pos);
      assertThat(user.getRole(), Matchers.equalTo(pos));
      assertThat(user.getRole(), Matchers.instanceOf(Role.class));
   }

   @Test
   public void userHasOrganization() {
      //Checks that user has organization property and it is correct
      Organization org = new Organization("Test LLC", "123 Test Street", "0768375617");
      user.setOrganization(org);
      assertThat(user.getOrganization().getAddress(), Matchers.equalTo("123 Test Street"));
   }

   @Test
   public void userHasTimesheets() {
      //Checks that user has timesheets
      Shift s1 = new Shift(LocalDate.of(2018, 10, 10), LocalTime.MIDNIGHT, LocalTime.NOON, 5, true, false);
      Timesheet timesheet = new Timesheet();

      timesheet.setTimesheetId(345);
      timesheet.setUser(user);
      timesheet.getShifts().add(s1);
      user.getTimesheets().add(timesheet);
      assertThat(user.getTimesheets(), Matchers.instanceOf(ArrayList.class));
   }
}

