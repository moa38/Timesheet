package springData.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springData.domain.User;
import springData.repository.UserRepository;

import java.security.Principal;
import java.util.List;

@Controller
public class AuthenticationController {
   @Autowired UserRepository userRepo;

   @RequestMapping(path = "/", method = RequestMethod.GET)
   public String landing() {
      return "login";
   }

   @RequestMapping (value = "/dashboard", method = RequestMethod.GET)
   public String dashboard(Model model, Principal principal) {
      User user = userRepo.findByUsername(principal.getName());
      model.addAttribute("name", user.getFirstName());
      return "/user/dashboard";
   }

   /*@RequestMapping(value="/edit", method=RequestMethod.POST, params="action=save")
   public ModelAndView save() {}


   @RequestMapping(value="/edit", method=RequestMethod.POST, params="action=cancel")
   public ModelAndView cancel() {}
    */
}
