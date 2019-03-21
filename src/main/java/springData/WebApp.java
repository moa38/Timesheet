package springData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springData.repository.UserRepository;
import springData.domain.User;

@SpringBootApplication
public class WebApp implements CommandLineRunner {

   @Autowired
   UserRepository userRepo;
   
   public static void main(String[] args) {
      SpringApplication.run(WebApp.class, args);
   }

   @Override
   public void run(String... args) throws Exception {
      // TODO Auto-generated method stub
   }

}
