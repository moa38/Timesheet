package springData.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springData.DTO.ShiftsDTO;
import springData.domain.Shift;
import springData.domain.Timesheet;
import springData.repository.ShiftRepository;
import springData.repository.TimesheetRepository;
import springData.repository.UserRepository;

@Controller
public class TimesheetController {

   @Autowired UserRepository userRepo;
   @Autowired ShiftRepository shiftRepo;
   @Autowired TimesheetRepository timesheetRepo;

   @InitBinder
   protected void initBinder(WebDataBinder binder) {
      binder.addValidators(new ShiftsDTOValidator());
   }

   @GetMapping(value = "/user/add-timesheet")
   public String addTimesheet(Model model) {
      //Add DTO to form
      ShiftsDTO shifts = new ShiftsDTO();

      for (int i = 0; i < 5; i++) {
         shifts.addShift(new Shift());
         System.out.println("Added " + i + " shift!!!");
      }
      model.addAttribute("shifts", shifts);

      return "user/add-timesheet";
   }

   @PostMapping(value = "/user/add-timesheet/saveTimesheet")
   public String saveTimesheet(@Valid @ModelAttribute("shifts") ShiftsDTO shifts, BindingResult result, Model model,
           Principal principal, @RequestParam(value = "action", required = true) String action) {

      if (result.hasErrors()) {
         return "user/add-timesheet";
      } else {
         Timesheet timesheet = new Timesheet();
         timesheet.setUser(userRepo.findByUsername(principal.getName()));
         timesheet.setShifts(shifts.getShifts());

         //Bind Each Shift to Timesheet
         for (int i = 0; i < 5; i++) {
            shifts.getShifts().get(i).setTimesheet(timesheet);
            /*Shift s = shifts.getShifts().get(i);
            System.out.println(s.getStartTime().until(s.getEndTime(), MINUTES));
            System.out.println("Minutes "+MINUTES.between(s.getStartTime(), s.getEndTime()));
            System.out.println("Hours " + HOURS.between(s.getStartTime(), s.getEndTime()));*/
         }
         //Submit button action
         if (action.equals("submit")) {
            timesheet.setSubmitted(true);
            timesheet.setDateSubmitted(LocalDate.now());
         }

         //Persist Timesheet & Shifts
         timesheet.setStartDate(shifts.getShifts().get(0).getShiftDate());
         timesheetRepo.save(timesheet);

         return "redirect:/dashboard";
      }
   }

   @RequestMapping("/user/view-all-timesheets")
   public String viewAllTimesheets(Model model, Principal principal) {
      //List of User's Timesheets
      List<Timesheet> timesheets = (List<Timesheet>) timesheetRepo.findAllByUser(
                userRepo.findByUsername(principal.getName()));

      model.addAttribute("timesheets", timesheets);

      return "user/view-all-timesheets";
   }

   @RequestMapping("/user/submitted-timesheets")
   public String submittedTimesheets(Model model, Principal principal) {
      //List of User's Timesheets
      List<Timesheet> timesheets = (List<Timesheet>) timesheetRepo.findAllSubmittedByUser(
              userRepo.findByUsername(principal.getName()));

      model.addAttribute("timesheets", timesheets);

      return "user/submitted-timesheets";
   }

   @GetMapping("/user/edit-timesheet/{timesheetId}")
   public String editTimesheet(@PathVariable int timesheetId, Model model) {
      //Find Timesheet by ID
      Timesheet timesheet = timesheetRepo.findById(timesheetId);

      //Add shifts to form
      ShiftsDTO shifts = new ShiftsDTO(shiftRepo.findByTimesheetId(timesheet));
      shifts.setTimesheetId(timesheet.getTimesheetId());

      model.addAttribute("shifts", shifts);

      return "user/edit-timesheet";
   }

   @PostMapping("/user/updateTimesheet")// {timesheetId}
   public String updateTimesheet(@Valid @ModelAttribute("shifts") ShiftsDTO shifts, BindingResult result, Model model,
           Principal principal, @RequestParam(value = "action", required = true) String action) {

      if (result.hasErrors()) {
         return "user/edit-timesheet";
      }

      Timesheet timesheet;

      //Get shifts from ShiftsDTO
      List<Shift> newShifts = shifts.getShifts();

      //Update Timesheet
      if (timesheetRepo.findByStartDate(shifts.getShifts().get(0).getShiftDate()) != null) {
         timesheet = timesheetRepo.findByStartDate(shifts.getShifts().get(0).getShiftDate());
         List<Shift> existingShifts = timesheet.getShifts();

         //Update existing shifts with new details
         for (int i = 0; i < existingShifts.size(); i++) {
            existingShifts.get(i).setShiftDate(newShifts.get(i).getShiftDate());
            existingShifts.get(i).setStartTime(newShifts.get(i).getStartTime());
            existingShifts.get(i).setEndTime(newShifts.get(i).getEndTime());
         }
      } else {
         //StartDate is changed -> Create new Timesheet
         timesheet = new Timesheet();
         timesheet.setUser(userRepo.findByUsername(principal.getName()));
         timesheet.setStartDate(newShifts.get(0).getShiftDate());
         timesheet.setShifts(newShifts);

         //Bind Each Shift to Timesheet
         for (int i = 0; i < 5; i++) {
            shifts.getShifts().get(i).setTimesheet(timesheet);
         }
      }
      //Submit button action
      if (action.equals("submit")) {
         timesheet.setSubmitted(true);
         timesheet.setDateSubmitted(LocalDate.now());
      }
      //Save Timesheet
      timesheetRepo.save(timesheet);

      return "redirect:/dashboard";
   }

   @GetMapping("/user/view-timesheet/{timesheetId}")
   public String viewTimesheet(@PathVariable int timesheetId, Model model) {
      //Find Timesheet by ID
      Timesheet timesheet = timesheetRepo.findById(timesheetId);

      //Add shifts to form
      List<Shift> shifts = timesheet.getShifts();

      model.addAttribute("shifts", shifts);

      return "user/view-timesheet";
   }

   @RequestMapping("/user/view-timesheet/back")
   public String viewTimesheetBack() {
      return "redirect:/user/submitted-timesheets";
   }

   //TODO make it so timesheetId is reclaimed for reuse in the DB
   @GetMapping("/user/delete-timesheet/{timesheetId}")
   public String deleteTimesheet(@PathVariable int timesheetId) {
      //Find Timesheet by @PathVariable
      Timesheet timesheet = timesheetRepo.findById(timesheetId);

      //Drop Timesheet & Shifts from database
      timesheetRepo.delete(timesheet);

      return "redirect:/user/view-all-timesheets";
   }

}
//ShiftController
