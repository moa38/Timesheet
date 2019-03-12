package springData.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import springData.domain.Timesheet;
import springData.domain.User;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Integer> {
   //List<Timesheet> findById(int id);
   //List<Timesheet> findByUser(User o);
   
   @Query("Select s from Timesheet s where s.timesheetId= :id")
   Timesheet findById(@Param("id") int id);
   
   @Query("Select e from Timesheet e where e.user=: u")
   Timesheet findByUser(@Param("user") User u);

   @SuppressWarnings("unchecked")
   Timesheet save(Timesheet timesheet);
}
