package springData;

import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import springData.domain.User;
import springData.domain.Shift;

public class UserTest {
   User user;
   
   @Before
   public void setUpUser() {
      user = new User(003, "John", "Smith", "abcde");
   }
   
   @Test
   public void userHasId() {
      //Checks that user has userId property
      assertThat(user, Matchers.hasProperty("userId", Matchers.equalTo(003)));
   }

   @Test
   public void userHasFirstLastName() {
      //Checks that user has first & last name properties
      assertThat(user, Matchers.hasProperty("firstName"));
      assertThat(user, Matchers.hasProperty("lastName", Matchers.equalTo("Smith")));
   }
   
   @Test
   public void positionHasUserList() {
      //Checks that user has a populated list of shifts
      //Create list and shifts
      LocalDateTime startTime = LocalDateTime.of(2019, Month.JANUARY, 4, 2, 44);
      LocalDateTime endTime = LocalDateTime.now();
      ArrayList<Shift> shifts = new ArrayList<Shift>();
      Shift s1 = new Shift(5465, startTime, endTime, 3, false, false);
      Shift s2 = new Shift();
      
      //Populate list with shifts
      user.setShifts(shifts);
      user.addShift(s1);
      user.addShift(s2);
      
      //Check that first shift has the correct startTime
      assertThat(user.shifts.get(0), Matchers.hasProperty("startTime", Matchers.equalTo(startTime)));
      
      //Remove s1 and check if list has correct size(1)
      user.removeShift(s1);
      assertThat(user.getShifts(), Matchers.hasSize(1));
   }
   
}
