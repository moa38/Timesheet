package springData.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import springData.domain.Shift;
import springData.domain.Timesheet;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Integer> {

   @Query("Select s from Shift s where s.shiftId= :id")
   Shift findById(@Param("id") int id);

   @Query("Select s from Shift s where s.shiftDate=: s")
   Shift findByDate(@Param("date") Date d);
   
   @Query("Select e from Shift e where e.timesheetId= :timesheetId")
   Shift findShiftByTimesheetId(@Param("timesheetId") Timesheet timesheetId);
   
   @Query("Select e from Shift e where e.timesheetId= :timesheetId")
   List<Shift> findByTimesheetId(@Param("timesheetId") Timesheet timesheetId);

   List<Shift> findAll();
   
   @SuppressWarnings("unchecked")
   Shift save(Shift shift);
}
