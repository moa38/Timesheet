package springData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import springData.repository.UserRepository;
import springData.repository.OrganizationRepository;
import springData.repository.ShiftsRepository;
import springData.repository.PositionRepository;
import springData.domain.*;

@SpringBootApplication
public class WebApp implements CommandLineRunner {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private OrganizationRepository orgRepo;
    @Autowired
    private ShiftsRepository shiftsRepo;
    @Autowired
    private PositionRepository positionRepo;

    public static void main(String[] args) {
        SpringApplication.run(WebApp.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

        // WEBAPP FILE WITH MAIN
        // TODO
        User user = new User();
        user.setUsername("admin");
        springData.domain.Position position = new Position();
        position.setId(0);
        position.save(user);

        user.setRole(role);
        userRepo.save(user);

        role = new Role();
        role.setRole("MANAGER");
        role.setId(1);
        roleRepo.save(role);

        role = new Role();
        role.setRole("ASSISTANT");
        role.setId(2);
        roleRepo.save(role);
    }
}