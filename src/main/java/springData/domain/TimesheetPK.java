package springData.domain;

import java.io.Serializable;
import java.util.Objects;

public class TimesheetPK implements Serializable {

   private static final long serialVersionUID = 714077079009152031L;

   private int timesheetId;

   private int user;

   public TimesheetPK() {
   }

   public TimesheetPK(int timesheetId, int user) {
      this.timesheetId = timesheetId;
      this.user = user;
   }

   public int getTimesheetId() {
      return this.timesheetId;
   }

   public void setTimesheetId(int timesheetId) {
      this.timesheetId = timesheetId;
   }

   public int getUser() {
      return this.user;
   }

   public void setUser(int user) {
      this.user = user;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      }
      if (!Objects.equals(getClass(), obj.getClass())) {
         return false;
      }
      final TimesheetPK other = (TimesheetPK) obj;
      if (!java.util.Objects.equals(this.getTimesheetId(), other.getTimesheetId())) {
         return false;
      }
      if (!java.util.Objects.equals(this.getUser(), other.getUser())) {
         return false;
      }
      return true;
   }

   @Override
   public int hashCode() {
      int hash = 7;
      hash = 31 * hash + this.getTimesheetId();
      hash = 31 * hash + Objects.hashCode(this.getUser());
      return hash;
   }

   @Override
   public String toString() {
      return "TimesheetPK{" + " timesheetId=" + timesheetId + ", user=" + user + '}';
   }

}
