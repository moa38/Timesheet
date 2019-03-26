package springData.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

 /*  @RequestMapping(value="/login", method=RequestMethod.GET)
   public String successLogin() { //HttpServletRequest httpServletRequest, Authentication authentication
      User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      springData.domain.User user = userRepo.findByUsername(authUser.getUsername());

      return "/user/add-shift";
   }*/

   // @RequestMapping(value= "/login", method = RequestMethod.GET)
   //public String printUser(Model model){
   // AppUser user = (AppUser) SecurityContextHolder.getContext().getAuthentication.getPrincipal();
   //String id = user.getUserId();
   //model.addAttribute("Hi", id);
   //return "dashboard";
   //}
   // this is what i have got for getting the users name, cant get it working atm
   /*@RequestMapping (value = "/dashboard", method = RequestMethod.POST)
    @ResponseBody
      public String currentUser (Principal principal){
       return principal.getuserId();
   }
    */
   // this is the outline for the save and cancel in edit page- may need to be moved

   /*@RequestMapping(value="/edit", method=RequestMethod.POST, params="action=save")
public ModelAndView save() {}


@RequestMapping(value="/edit", method=RequestMethod.POST, params="action=cancel")
public ModelAndView cancel() {}
    */
}
