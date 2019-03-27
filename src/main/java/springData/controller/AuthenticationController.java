package springData.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springData.repository.UserRepository;

@Controller
public class AuthenticationController {
   @Autowired UserRepository userRepo;

   @RequestMapping(path = "/", method = RequestMethod.GET)
   public String landing() {
      return "login";
   }

   @RequestMapping (value = "/dashboard", method = RequestMethod.GET)
   public String dashboard(Model model, Principal principal) {
      model.addAttribute("name", principal.getName());
      return "/user/dashboard";
   }

   /*@RequestMapping(value="/edit", method=RequestMethod.POST, params="action=save")
   public ModelAndView save() {}


   @RequestMapping(value="/edit", method=RequestMethod.POST, params="action=cancel")
   public ModelAndView cancel() {}
    */
}
