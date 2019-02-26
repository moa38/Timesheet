package springData;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springData.domain.Organization;
import springData.domain.Position;
import springData.domain.Shift;
import springData.domain.User;

import springData.repository.UserRepository;
import springData.repository.OrganizationRepository;
import springData.repository.ShiftRepository;
import springData.repository.PositionRepository;

@SpringBootApplication
public class WebApp implements CommandLineRunner {

   @Autowired
   private UserRepository userRepo;
   @Autowired
   private OrganizationRepository orgRepo;
   @Autowired
   private ShiftRepository shiftRepo;
   @Autowired
   private PositionRepository positionRepo;

   public static void main(String[] args) {
      SpringApplication.run(WebApp.class, args);
   }

   @Override
   public void run(String... args) throws Exception {
      User vinny = new User();
      vinny.setFirstName("Vinny");
      vinny.setLastName("Barden");
      vinny.setPassword("password");
      vinny.setUserId(001);
      userRepo.save(vinny);

      User bob = new User();
      bob.setFirstName("Bob");
      bob.setLastName("Ross");
      bob.setPassword("password");
      bob.setUserId(002);
      userRepo.save(bob);

      Position examplePosition = new Position();
      examplePosition.setPositionId(001);
      examplePosition.setPositionName("student");
      examplePosition.addUser(vinny);
      examplePosition.addUser(bob);
      positionRepo.save(examplePosition);

      Shift exampleShift = new Shift();
      exampleShift.setShiftId(001);
      exampleShift.setStartTime(LocalDateTime.now());
      shiftRepo.save(exampleShift);

      Organization exampleOrganization = new Organization();
      exampleOrganization.setOrganizationId(001);
      exampleOrganization.setName("UoL");
      exampleOrganization.setContactNumber("01161234567");
      exampleOrganization.setAddress("University Road, Leicester");
      exampleOrganization.addUser(vinny);
      exampleOrganization.addUser(bob);
      orgRepo.save(exampleOrganization);
   }
}
