package springData.controller;

import java.security.Principal;
import java.time.LocalTime;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import springData.domain.Shift;

@Controller
public class UserController {
  // @InitBinder
   //protected void initBinder(WebDataBinder binder) {
    //  binder.addValidators(new UserValidator());
   //}
   
   @GetMapping("/add-shift")
   public String addShift(@Valid Shift shift, Model model) {
      /*
      shift.setStartTime(LocalTime.of(9, 00));
      shift.setEndTime(LocalTime.of(17, 0));
      model.addAttribute("start Time", shift.getStartTime());
      model.addAttribute("end Time", shift.getEndTime());
      model.addAttribute("id", shift.getShiftId());
*/
      //shiftRepo.save(shift);
      //model.addAttribute("shifts", shiftRepo.findAll());
      return "user/add-shift";
   }

}
