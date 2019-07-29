package springData.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import springData.domain.Timesheet;
import springData.domain.User;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Integer> {

   @Query("Select s from Timesheet s where s.timesheetId= :id")
   Timesheet findById(@Param("id") int id);

   @Query("Select e from Timesheet e where e.user=: u")
   List<Timesheet> findByUser(@Param("user") User u);

   List<Timesheet> findAll();

   //DELETE FROM Child c WHERE c.mother <the condition>

   @Query("Select e from Timesheet e where e.approved = TRUE")
   List<Timesheet> findAllApproved();

   @Query("Select e from Timesheet e where e.submitted = TRUE AND e.approved = FALSE")
   List<Timesheet> findAllSubmitted();

   @Query("Select e from Timesheet e where e.submitted = TRUE AND e.user= :user")
   List<Timesheet> findAllSubmittedByUser(@Param("user") User user);

   @Query("Select e from Timesheet e where e.user= :user")
   List<Timesheet> findAllByUser(@Param("user") User user);

   @Query("Select e from Timesheet e where e.startDate= :startDate")
   Timesheet findByStartDate(@Param("startDate") LocalDate startDate);

   @SuppressWarnings("unchecked")
   Timesheet save(Timesheet timesheet);
}
