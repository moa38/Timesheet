package springData.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * Timesheet class contatining 7 shifts representing the days of the week.
 *
 * @author CO2015 Group-17
 */
@Entity(name = "Timesheet")
@Table(name = "Timesheet")
@IdClass(TimesheetPK.class)
@NamedQuery(name = "Timesheet.findAll", query = "Select e from Timesheet e")
@NamedQuery(name = "Timesheet.findById", query = "Select e from Timesheet e where e.timesheetId=:timesheetId")
public class Timesheet {

   @Column(name = "Timesheet_ID", unique = true, updatable = false, nullable = false)
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int timesheetId;

   @Id
   @ManyToOne(fetch = FetchType.EAGER)
   private User user;

   @Type(type = "yes_no")
   @Column(name = "Submitted", nullable = false)
   private boolean submitted;

   @Type(type = "yes_no")
   @Column(name = "Approved", nullable = false)
   private boolean approved;

   @OneToMany(mappedBy = "timesheet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   private List<Shift> shifts;

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
      if (shifts == null) {
         shifts = new ArrayList<>();
      }
      return this.shifts;
   }

   public void setShifts(List<Shift> shifts) {
      this.shifts = shifts;
   }

   public void addShift(Shift shift) {
      getShifts().add(shift);
      shift.setTimesheet(this);
   }

   public void removeShift(Shift shift) {
      getShifts().remove(shift);
      shift.setTimesheet(null);
   }

   @Override
   public String toString() {
      return "Timesheet [timesheetId=" + timesheetId + ", user=" + user + ", submitted=" + submitted
            + ", approved=" + approved + ", shifts=" + shifts + "]";
   }

}

