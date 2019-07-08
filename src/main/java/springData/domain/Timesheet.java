package springData.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

@Entity
public class Timesheet {

   @Id
   @Column(name = "Timesheet_ID", unique = true, updatable = false, nullable = false)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "timesheet_generator")
   private int timesheetId;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="USER_ID")
   private User user;

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

   @Override
   public String toString() {
      return "Timesheet [timesheetId=" + timesheetId + ", user=" + user + ", submitted=" + submitted
            + ", approved=" + approved + "]";
   }

}

