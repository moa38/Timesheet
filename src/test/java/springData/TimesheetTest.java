package springData;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;

import springData.domain.User;
import springData.domain.Shift;
import springData.domain.Timesheet;

public class TimesheetTest {
   Timesheet timesheet;

   @Before
   public void setUpTimesheet() {
      //Create new Timesheet to be tested
      timesheet = new Timesheet();
      List<Shift> shifts = new ArrayList<Shift>();
      User user = new User("John", "Smith", "abcde");
      timesheet.setTimesheetId(3464567);
      timesheet.setUser(user);
      timesheet.setShifts(shifts);
   }

   @Test
   public void timesheetHasId() {
      //Checks that Timesheet has timesheetId property
      assertThat(timesheet, Matchers.hasProperty("timesheetId", Matchers.equalTo(3464567)));

      //Check that setTimesheetId() works as intended
      timesheet.setTimesheetId(40058);
      assertThat(timesheet, Matchers.hasProperty("timesheetId", Matchers.not(Matchers.equalTo(34645667))));
   }

   @Test
   public void timesheetHasUser() {
      //Checks that Timesheet has User
      assertThat(timesheet.getUser(), Matchers.instanceOf(User.class));

      //Check that setAppUser() works as intended
      User user2 = new User("Bob", "Bobson", "edcba");
      timesheet.setUser(user2);
      assertThat(timesheet.getUser(), Matchers.equalTo(user2));
      assertThat(timesheet.getUser().getFirstName(), Matchers.equalTo("Bob"));
   }

   @Test
   public void timesheetHasShifts() {
      //Checks that Timesheet has Shifts
      Shift s1 = new Shift(LocalDate.of(2018, 10, 10), LocalTime.MIDNIGHT, LocalTime.NOON, 5, true, false);
      Shift s2 = new Shift(LocalDate.of(2018, 10, 11), LocalTime.NOON, LocalTime.NOON, 0, false, true);
      Shift s3 = new Shift(LocalDate.of(2018, 10, 12), LocalTime.MIDNIGHT, LocalTime.NOON, 1, false, false);
      System.out.println(s3.getShiftId());
      //Check that getShifts() works as intended
      timesheet.addShift(s1);
      assertThat(timesheet.getShifts(), Matchers.hasSize(1));
      timesheet.addShift(s2);
      timesheet.addShift(s3);
      assertThat(timesheet.getShifts(), Matchers.hasSize(3));
      assertThat(timesheet.getShifts().get(2), Matchers.equalTo(s3));
   }

}

