package springData.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springData.domain.Shift;
import springData.domain.Timesheet;
import springData.domain.User;
import springData.repository.ShiftRepository;
import springData.repository.TimesheetRepository;
import springData.repository.UserRepository;
//import springData.repository.ShiftRepository;

@Controller
public class ShiftController {

   @Autowired ShiftRepository shiftRepo;
   @Autowired TimesheetRepository timesheetRepo;
   @Autowired UserRepository userRepo;

   /*DO NOT ALTER THESE METHODS, I WILL GET BACK TO THEM AFTER THE VIEWS ARE UPDATED
   /*@GetMapping(value = "/add-shift")
   public String addShift(Model model, Principal principal) {
      User user = userRepo.findByLogin(principal.getName());
      Timesheet ts = new Timesheet();
      List<Shift> shifts = new ArrayList<Shift>();
      model.addAttribute("shifts", shifts);
      model.addAttribute("timesheet", ts);

      return "user/add-shift";
   }*/

   //@RequestMapping(value = "/add-shift", method= RequestMethod.GET)
   @GetMapping(value = "/add-shift")
   public String addShift(Model model, Principal principal) {
      //User user = userRepo.findByLogin(principal.getName());

      Timesheet ts = new Timesheet();
      List<Shift> shifts = new ArrayList<Shift>();

      Shift mon= new Shift();
      Shift tue = new Shift();
      Shift wed = new Shift();
      Shift thu = new Shift();
      Shift fri = new Shift();

      mon.setShiftId(01);
      tue.setShiftId(02);
      wed.setShiftId(03);
      thu.setShiftId(04);
      fri.setShiftId(05);

      shifts.add(mon);
      shifts.add(tue);
      shifts.add(wed);
      shifts.add(thu);
      shifts.add(fri);
      ts.getShifts().addAll(shifts);
      //DO NOT ALTER THESE
      //ts.setTimesheetId(timesheet.getTimesheetId());
      //model.addAttribute("Monday", mon);
      //model.addAttribute("Tuesday", tue);
      //model.addAttribute("shift", wed);
      //model.addAttribute("shift", thu);
      model.addAttribute("shift", fri);
      //model.addAttribute("shifts", shifts);
      model.addAttribute("timesheet", ts);
      //model.addAttribute("timesheet", (List<Timesheet>)timesheetRepo.findAll());

      return "user/add-shift";
   }

   /*DO NOT ALTER THESE METHODS, I WILL GET BACK TO THEM AFTER THE VIEWS ARE UPDATED
   @PostMapping("/add-shift/saveShift")
   public String saveShift(@ModelAttribute("shift") Shift shift, Model model) {

      //Shift s = new Shift();
      shift.setShiftDate(LocalDate.of(2018, 03, 20));
      //s.setShiftDate(shift.getShiftDate());
      //s.setStartTime(shift.getStartTime());
      //s.setEndTime(shift.getEndTime());
      //s.setTimesheet(timesheet.getTimesheetId());
      shiftRepo.save(shift);

      model.addAttribute("startTime", shift.getStartTime());
      model.addAttribute("endTime", shift.getEndTime());
      return "user/add-shift";
   }

   @PostMapping("/add-shift/saveShift/{shiftId}")
   public String saveShiftId(@PathVariable int shiftId, @ModelAttribute("shift") Shift shift, Model model) {

      Shift p = shiftRepo.findById(shiftId);
      if (p != null) shiftRepo.delete(shift);
      shift.setShiftId(shiftId);
      //Shift s = new Shift();
      shift.setShiftDate(LocalDate.of(2018, 03, 20));
      //s.setShiftDate(shift.getShiftDate());
      //s.setStartTime(shift.getStartTime());
      //s.setEndTime(shift.getEndTime());
      //s.setTimesheet(timesheet.getTimesheetId());
      shiftRepo.save(shift);

      model.addAttribute("startTime", shift.getStartTime());
      model.addAttribute("endTime", shift.getEndTime());
      return "user/dashboard";
   }*/

   @PostMapping("/add-shift/saveTimesheet")
   public String saveTimesheet(@ModelAttribute ("timesheet") Timesheet timesheet, Model model) {
      List<Timesheet> timesheets = new ArrayList<Timesheet>();
      model.addAttribute("timesheet", timesheet); 
      
      return "user/edit-shift";
   }

}
