package springData.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Timesheet {

   @Id
   @Column(name = "Timesheet_ID", unique = true, updatable = false, nullable = false)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "timesheet_generator")
   private int timesheetId;

   @ManyToOne(optional = false, fetch = FetchType.LAZY)
   @JoinColumn(name="USER_ID")
   private User user;
   
   @Column(nullable=false)
   @DateTimeFormat(iso = ISO.DATE)
   private LocalDate startDate;
   
   @Column
   @DateTimeFormat(iso = ISO.DATE)
   private LocalDate dateSubmitted;

   @OneToMany(mappedBy="timesheetId", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
   private List<Shift> shifts = new ArrayList<Shift>();

   @Type(type = "yes_no")
   @Column(name = "Submitted", nullable = false)
   private boolean submitted;

   @Type(type = "yes_no")
   @Column(name = "Approved", nullable = false)
   private boolean approved;

   public int getTimesheetId() {
      return this.timesheetId;
   }

   public void setTimesheetId(int timesheetId) {
      this.timesheetId = timesheetId;
   }

   public boolean isSubmitted() {
      return this.submitted;
   }

   public void setSubmitted(boolean submitted) {
      this.submitted = submitted;
   }

   public boolean isApproved() {
      return this.approved;
   }

   public void setApproved(boolean approved) {
      this.approved = approved;
   }

   public User getUser() {
      return this.user;
   }

   public void setUser(User user) {
      this.user = user;
   }
   
   public List<Shift> getShifts() {
      return shifts;
   }

   public void setShifts(List<Shift> shifts) {
      this.shifts = shifts;
   }

   public LocalDate getStartDate() {
      return startDate;
   }

   public void setStartDate(LocalDate startDate) {
      this.startDate = startDate;
   }
   
   public LocalDate getDateSubmitted() {
      return dateSubmitted;
   }

   public void setDateSubmitted(LocalDate dateSubmitted) {
      this.dateSubmitted = dateSubmitted;
   }

   @Override
   public String toString() {
      return "Timesheet [timesheetId=" + timesheetId + ", user=" + user + ", submitted=" + submitted
            + ", approved=" + approved + "]";
   }

}

