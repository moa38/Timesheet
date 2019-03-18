package springData.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import springData.domain.Shift;
import springData.domain.Timesheet;
import springData.repository.ShiftRepository;
import springData.repository.TimesheetRepository;
//import springData.repository.ShiftRepository;

@Controller
public class ShiftController {
   
   @Autowired ShiftRepository shiftRepo;
   @Autowired TimesheetRepository timesheetRepo;

   @RequestMapping(value = "/add-shift")
   public String addShift(Model model) {
      model.addAttribute("shift", new Shift());
      model.addAttribute("timesheet", new Timesheet());
      return "user/add-shift";
   }

   @PostMapping("/add-shift/saveShift")
   public String saveShift(@ModelAttribute("shift") Shift shift, Model model) {
      Shift s = new Shift();
      s.setShiftDate(shift.getShiftDate());
      s.setStartTime(shift.getStartTime());
      s.setEndTime(shift.getEndTime());
      
      /*shiftRepo.save(s);

      model.addAttribute("timesheet", (List<Timesheet>)timesheetRepo.findAll());

      model.addAttribute("startTime", s.getStartTime());
      model.addAttribute("endTime", s.getEndTime());*/
      return "user/add-shift";
   }

   @PostMapping("/add-shift/saveTimesheet")
   public String saveTimesheet(@ModelAttribute Timesheet timesheet, Model model) {
      return "user/add-shift";
   }

}
