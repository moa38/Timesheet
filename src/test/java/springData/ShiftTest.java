package springData;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.Month;

import springData.domain.Shift;

public class ShiftTest {
   Shift shift;

   @Before
   public void setUpShift() {
      LocalDateTime startTime = LocalDateTime.now();
      LocalDateTime endTime = LocalDateTime.now();
      shift = new Shift(5465, startTime, endTime, 5, true, false);
   }

   @Test
   public void shiftHasId() {
      //Checks that shift has shiftId property
      assertThat(shift, Matchers.hasProperty("shiftId", Matchers.equalTo(5465)));
   }

   @Test
   public void shiftHaStartTime() {
      //Checks that shift has startTime property and it is correct
      LocalDateTime specificTime = LocalDateTime.of(2019, Month.FEBRUARY, 1, 10, 34);
      shift.setStartTime(specificTime);
      assertThat(shift, Matchers.hasProperty("startTime", Matchers.equalTo(specificTime)));
   }

   @Test
   public void shiftHasEndTime() {
      //Checks that shift has endTime property and it is correct
      LocalDateTime specificTime = LocalDateTime.of(2019, Month.JANUARY, 4, 10, 54);
      shift.setStartTime(specificTime);
      assertThat(shift, Matchers.hasProperty("startTime", Matchers.equalTo(specificTime)));
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
