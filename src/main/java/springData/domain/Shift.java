package springData.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Shift class representing a single work day
 *
 * @author CO2015 Group-17
 */
@Entity(name = "Shift")
@Table(name = "Shift")
@NamedQuery(name = "Shift.findById", query = "Select s from Shift s where s.shiftId=:shiftId")
public class Shift {

   @Column(unique = true, updatable = false, nullable = false)
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int shiftId;

   @Column(unique = true, nullable = false)
   @Basic
   private LocalDate shiftDate;

   @Basic
   private LocalTime startTime = LocalTime.of(9, 0);

   @Basic
   private LocalTime endTime = LocalTime.of(17, 0);

   @Basic
   private int overtimeHours = 0;

   @Basic
   private boolean bankHoliday = false;

   @Basic
   private boolean holiday = false;

   @ManyToOne
   private Timesheet timesheet;

   public Shift(LocalDate shiftDate, LocalTime startTime, LocalTime endTime, int overtimeHours, boolean bankHoliday,
           boolean holiday) {
      this.shiftDate = shiftDate;
      this.startTime = startTime;
      this.endTime = endTime;
      this.overtimeHours = overtimeHours;
      this.bankHoliday = bankHoliday;
      this.holiday = holiday;
   }

   public Shift() {
   }

   public int getShiftId() {
      return this.shiftId;
   }

   public void setShiftId(int shiftId) {
      this.shiftId = shiftId;
   }

   public LocalDate getShiftDate() {
      return this.shiftDate;
   }

   public void setShiftDate(LocalDate shiftDate) {
      this.shiftDate = shiftDate;
   }

   public LocalTime getStartTime() {
      return this.startTime;
   }

   public void setStartTime(LocalTime startTime) {
      this.startTime = startTime;
   }

   public LocalTime getEndTime() {
      return this.endTime;
   }

   public void setEndTime(LocalTime endTime) {
      this.endTime = endTime;
   }

   public int getOvertimeHours() {
      return this.overtimeHours;
   }

   public void setOvertimeHours(int overtimeHours) {
      this.overtimeHours = overtimeHours;
   }

   public boolean isBankHoliday() {
      return this.bankHoliday;
   }

   public void setBankHoliday(boolean bankHoliday) {
      this.bankHoliday = bankHoliday;
   }

   public boolean isHoliday() {
      return this.holiday;
   }

   public void setHoliday(boolean holiday) {
      this.holiday = holiday;
   }

   public Timesheet getTimesheet() {
      return this.timesheet;
   }

   public void setTimesheet(Timesheet timesheet) {
      this.timesheet = timesheet;
   }

   @Override
   public String toString() {
      return "Shift [shiftId=" + shiftId + ", shiftDate=" + shiftDate + ", startTime=" + startTime + ", endTime="
            + endTime + ", overtimeHours=" + overtimeHours + ", bankHoliday=" + bankHoliday + ", holiday=" + holiday
            + ", timesheet=" + timesheet + "]";
   }

}

