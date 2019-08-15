package springData;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import springData.repository.RoleRepository;
import springData.repository.TimesheetRepository;
import springData.repository.UserRepository;
import springData.domain.Role;
import springData.domain.Shift;
import springData.domain.Timesheet;
import springData.domain.User;

@SpringBootApplication
public class WebApp implements CommandLineRunner {

   @Autowired UserRepository userRepo;
   @Autowired RoleRepository roleRepo;
   @Autowired TimesheetRepository timesheetRepo;

   public static void main(String[] args) {
      SpringApplication.run(WebApp.class, args);
   }

   @Override
   public void run(String... args) throws Exception {
      BCryptPasswordEncoder pe = new  BCryptPasswordEncoder();

      Role role = new Role(1, "USER");
      Role admin = new Role(2, "ADMIN");
      Role manager = new Role(3, "MANAGER");
      roleRepo.save(role);

      User user = new User();
      user.setFirstName("Bob");
      user.setLastName("Bobson");
      user.setUsername("bob@bobmail.com");
      user.setPassword(pe.encode("password"));
      user.setRole(admin);

      User user2 = new User();
      user2.setFirstName("John");
      user2.setLastName("Smith");
      user2.setUsername("smithy@mail.com");
      user2.setPassword(pe.encode("password2"));
      user2.setRole(manager);

      userRepo.save(user);
      userRepo.save(user2);

      Timesheet t = new Timesheet();
      t.setUser(user);
      t.setStartDate(LocalDate.of(2018, 8, 8));

      Shift s1 = new Shift();
      Shift s2 = new Shift();
      Shift s3 = new Shift();
      Shift s4 = new Shift();
      Shift s5 = new Shift();

      s1.setShiftDate(LocalDate.of(2012, 8, 8));
      s2.setShiftDate(LocalDate.of(2012, 8, 9));
      s3.setShiftDate(LocalDate.of(2012, 8, 10));
      s4.setShiftDate(LocalDate.of(2012, 9, 9));
      s5.setShiftDate(LocalDate.of(2012, 9, 10));

      s1.setTimesheet(t);
      s2.setTimesheet(t);
      s3.setTimesheet(t);
      s4.setTimesheet(t);
      s5.setTimesheet(t);

      t.getShifts().add(s1);
      t.getShifts().add(s2);
      t.getShifts().add(s3);
      t.getShifts().add(s4);
      t.getShifts().add(s5);
      t.setSubmitted(true);
      t.setApproved(true);

      timesheetRepo.save(t);

      userRepo.save(user);
      userRepo.save(user2);
   }

}
