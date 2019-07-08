package springData;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import springData.repository.TimesheetRepository;
import springData.repository.UserRepository;
import springData.domain.Role;
import springData.domain.Shift;
import springData.domain.Timesheet;
import springData.domain.User;

@SpringBootApplication
public class WebApp implements CommandLineRunner {

   @Autowired UserRepository userRepo;
   @Autowired TimesheetRepository timesheetRepo;

   public static void main(String[] args) {
      SpringApplication.run(WebApp.class, args);
   }

   @Override
   public void run(String... args) throws Exception {
      // TODO Auto-generated method stub
      BCryptPasswordEncoder pe = new  BCryptPasswordEncoder();

      Role role = new Role(1, "ROLE_USER");

      User user = new User();
      user.setFirstName("Bob");
      user.setLastName("Bobson");
      user.setUsername("bob@bobmail.com");
      user.setEncryptedPassword(pe.encode("password"));
      user.setRole(role);

      User user2 = new User();
      user2.setFirstName("John");
      user2.setLastName("Smith");
      user2.setUsername("smithy@mail.com");
      user2.setEncryptedPassword(pe.encode("password2"));
      //user2.setRole(role);

      Timesheet t = new Timesheet();

      Shift s1 = new Shift();
      Shift s2 = new Shift();
      Shift s3 = new Shift();
      s1.setShiftDate(LocalDate.of(2018, 8, 8));
      s1.setTimesheet(t);
      s2.setTimesheet(t);
      s3.setTimesheet(t);

      s1.setStartTime(LocalTime.NOON);
      s1.setEndTime(LocalTime.MIDNIGHT);

      //Doesn't save user correctly
      /*t.setUser(user);
      t.getShifts().add(s1);
      t.getShifts().add(s2);
      t.getShifts().add(s3);*/

      user.getTimesheets().add(t);
      //Cause of ghost user
      //timesheetRepo.save(t);

      userRepo.save(user);
      userRepo.save(user2);
   }

}
