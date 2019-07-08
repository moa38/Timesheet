package springData.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import static java.time.temporal.ChronoUnit.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import springData.DTO.ShiftsDTO;
import springData.domain.Shift;
import springData.domain.Timesheet;
import springData.domain.User;
import springData.repository.ShiftRepository;
import springData.repository.TimesheetRepository;
import springData.repository.UserRepository;

@Controller
public class ShiftController {

   @Autowired ShiftRepository shiftRepo;
   @Autowired TimesheetRepository timesheetRepo;
   @Autowired UserRepository userRepo;

   @GetMapping(value = "/add-timesheet")
   public String addTimesheet(Model model) {

      ShiftsDTO shifts = new ShiftsDTO();
      
      for (int i = 0; i < 5; i++) {
         shifts.addShift(new Shift());
         System.out.println("Added " + i + " shift!!!");
      }
      model.addAttribute("shifts", shifts);
      
      return "user/add-timesheet";
   }

   @PostMapping("/add-timesheet/saveTimesheet")
   public String saveTimesheet(@ModelAttribute("shifts") ShiftsDTO shifts, Model model, Principal principal) {

      Timesheet timesheet = new Timesheet();
      timesheet.setUser(userRepo.findByUsername(principal.getName()));
   
      //Bind Each Shift to Timesheet
      for(int i = 0; i < 5; i++) {
         shifts.getShifts().get(i).setTimesheet(timesheet);
         /*Shift s = shifts.getShifts().get(i);
         System.out.println(s.getStartTime().until(s.getEndTime(), MINUTES));
         System.out.println("Minutes "+MINUTES.between(s.getStartTime(), s.getEndTime()));
         System.out.println("Hours " + HOURS.between(s.getStartTime(), s.getEndTime()));*/
      }
      //Persist Timesheet & Shifts
      shifts.setTimesheetId(timesheet.getTimesheetId());
      timesheetRepo.save(timesheet);
      shiftRepo.saveAll(shifts.getShifts());
      
      return "redirect:/dashboard";
   }

   @RequestMapping("/view-timesheets")
   public String viewTimesheets(Model model, Principal principal) {
      
      List<Timesheet> timesheets = (List<Timesheet>) timesheetRepo.findAllByUser(userRepo.findByUsername(principal.getName()));
      model.addAttribute("timesheets", timesheets);
      
      return "user/view-timesheets";
   }

   @GetMapping("/edit-timesheet/{timesheetId}")
   public String editTimesheet(@PathVariable int timesheetId, Model model) {
      Timesheet timesheet = timesheetRepo.findById(timesheetId);
      ShiftsDTO shifts = new ShiftsDTO(shiftRepo.findByTimesheetId(timesheet));
      
      model.addAttribute("shifts", shifts);
      
      return "user/edit-timesheet";
   }
   
   //TODO make it so timesheetId is reclaimed for reuse in the DB
   @GetMapping("/delete-timesheet/{timesheetId}")
   public String deleteTimesheet(@PathVariable int timesheetId, Model model) {
      Timesheet timesheet = timesheetRepo.findById(timesheetId);
      List<Shift> shifts = shiftRepo.findByTimesheetId(timesheetRepo.findById(timesheetId));
      
      //Drop entities from database
      shiftRepo.deleteAll(shifts);
      timesheetRepo.delete(timesheet);
      
      return "redirect:/view-timesheets";
   }
   
   //TODO duplicate add-timesheet controller 
   @PostMapping("/updateTimesheet")
   public String updateTimesheet(@ModelAttribute("shifts") ShiftsDTO shifts, Model model, Principal principal) {
      ShiftsDTO newShifts = new ShiftsDTO(shifts.getShifts());
      
      //Timesheet timesheet = timesheetRepo.findById(timesheetId); //new Timesheet();
      System.out.println("THe sixesdf  is"+shifts.getShifts().get(0).getTimesheet().getTimesheetId());
      Timesheet timesheet = shifts.getShifts().get(0).getTimesheet();
      Shift fish = shiftRepo.findShiftByTimesheetId(timesheet);
      System.out.println("THe list mm is "+ fish.getStartTime());
      //timesheet.setUser(userRepo.findByUsername(principal.getName()));
      System.out.println("THe fish  is"+shifts.getShifts().size());
      List<Shift> mm = shiftRepo.findByTimesheetId(timesheet);
      System.out.println("THe list mm is "+mm.size());
      
      ShiftsDTO currentShifts = new ShiftsDTO(mm);
      System.out.println("THe flying fish  is "+currentShifts.getShifts().size());
      
      //Update Shifts
      for(int i = 0; i < 5; i++) {
         System.out.println("THe start  is"+shifts.getShifts().size());
         //Update Date
         newShifts.getShifts().get(i).getShiftDate();
         System.out.println("THe potatoe  is"+ newShifts.getShifts().size());
         currentShifts.getShifts().get(i).getShiftDate();
         
         currentShifts.getShifts().get(i).setShiftDate(newShifts.getShifts().get(i).getShiftDate());
         System.out.println("THe first  is"+shifts.getShifts().size());
         
         //Update Start Time
         currentShifts.getShifts().get(i).setStartTime(shifts.getShifts().get(i).getStartTime());
         System.out.println("THe second  is"+shifts.getShifts().size());
         
         //Update End Time
         currentShifts.getShifts().get(i).setEndTime(shifts.getShifts().get(i).getEndTime());
         
         
         System.out.println("THe finsih  is"+shifts.getShifts().size());
         shiftRepo.save(currentShifts.getShifts().get(i));
         System.out.println("THe save  is"+shifts.getShifts().size());
         //shifts.getShifts().get(i).setTimesheet(timesheet);
         
         /*Shift s = shifts.getShifts().get(i);
         System.out.println(s.getStartTime().until(s.getEndTime(), MINUTES));
         System.out.println("Minutes "+MINUTES.between(s.getStartTime(), s.getEndTime()));
         System.out.println("Hours " + HOURS.between(s.getStartTime(), s.getEndTime()));*/
      }
      //shifts.getShifts().get(0).setOvertimeHours(66);
      //shiftRepo.saveAll(currentShifts.getShifts());
      
      return "redirect:/dashboard";
   }
   
}
//ShiftController
