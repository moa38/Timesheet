package springData;

import static org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.web.context.WebApplicationContext

import springData.controller.OrganizationController
import springData.controller.PositionController
import springData.controller.ShiftsController
import springData.controller.UserController
import spock.lang.Specification

@ContextConfiguration
@WebMvcTest(controllers=[OrganizationController.class, PositionController.class, ShiftsController.class, 
   UserController.class])
class OrganizationSpec extends Specification {

   @Autowired
   private WebApplicationContext wac;
   
   private MockMvc mockMvc;
   private ResultActions result;
  
   @Test
   void test() {
      fail("Not yet implemented")
   }

}
