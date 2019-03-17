package springData;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import springData.domain.Shift;

public class ShiftTest {
   Shift shift;

   @Before
   public void setUpShift() {
      //Create new Shift to be tested
      LocalDate date = LocalDate.of(2015, 06, 13);
      LocalTime startTime = LocalTime.now();
      LocalTime endTime = LocalTime.now();
      //New shift
      shift = new Shift(date, startTime, endTime, 5, true, false);
   }

   @Test
   public void shiftHasId() {
      //Checks that shift has shiftId property
      shift.setShiftId(5465);
      assertThat(shift, Matchers.hasProperty("shiftId", Matchers.equalTo(5465)));
   }

   @Test
   public void shiftHaStartTime() {
      //Checks that shift has startTime property and it is correct
      LocalTime specificTime = LocalTime.of(10, 00);
      shift.setStartTime(specificTime);
      assertThat(shift, Matchers.hasProperty("startTime", Matchers.equalTo(LocalTime.of(10, 00))));
      //Change time and check
      specificTime = LocalTime.of(21, 56);
      shift.setStartTime(specificTime);
      assertThat(shift, Matchers.hasProperty("startTime", Matchers.equalTo(LocalTime.of(21, 56))));
   }

   @Test
   public void shiftHasEndTime() {
      //Checks that shift has endTime property and it is correct
      LocalTime specificTime = LocalTime.of(17, 30);
      shift.setStartTime(specificTime);
      assertThat(shift, Matchers.hasProperty("startTime", Matchers.equalTo(LocalTime.of(17, 30))));
   }

   @Test
   public void shiftHasOvertime() {
      //Checks that shift has overtime property
      shift.setOvertimeHours(5);
      assertThat(shift, Matchers.hasProperty("overtimeHours", Matchers.equalTo(5)));
      assertThat(shift, Matchers.hasProperty("overtimeHours", Matchers.not(Matchers.equalTo(8))));
   }

   @Test
   public void shiftHasBankHoliday() {
      //Checks that shift bank holiday is correct
      assertTrue(shift.isBankHoliday());
      shift.setBankHoliday(false);
      assertFalse(shift.isBankHoliday());
   }

   @Test
   public void shiftHasHoliday() {
      //Checks that shift Holiday is correct
      assertFalse(shift.isHoliday());
      shift.setHoliday(true);
      assertTrue(shift.isHoliday());
   }

}

