package springData.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Shift {

   @Id
   @Column(unique = true, nullable = false)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shift_generator")
   private int shiftId;

  
   @Column(nullable = false)
   @DateTimeFormat(iso = ISO.DATE)
   private LocalDate shiftDate;

   @Column
   @DateTimeFormat(iso = ISO.TIME)
   private LocalTime startTime = LocalTime.of(9, 0);

   @Column
   @DateTimeFormat(iso = ISO.TIME)
   private LocalTime endTime = LocalTime.of(17, 0);

   @Column
   private int overtimeHours = 0;

   @Type(type = "yes_no")
   @Column
   private boolean bankHoliday = false;

   //@Type(type = "yes_no")
   @Column
   private boolean holiday = false;

   @ManyToOne//(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
   @JoinColumn(name="Timesheet")
   private Timesheet timesheetId;

   public Shift(LocalDate shiftDate, LocalTime startTime, LocalTime endTime) {
      this.shiftDate = shiftDate;
      this.startTime = startTime;
      this.endTime = endTime;
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
      return this.timesheetId;
   }

   public void setTimesheet(Timesheet timesheet) {
      this.timesheetId = timesheet;
   }

   @Override
   public String toString() {
      return "Shift [shiftId=" + shiftId + ", shiftDate=" + shiftDate + ", startTime=" + startTime + ", endTime="
            + endTime + ", overtimeHours=" + overtimeHours + ", bankHoliday=" + bankHoliday + ", holiday=" + holiday
            + ", timesheet=" + timesheetId + "]";
   }

}

