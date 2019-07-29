package springData.controller;

import java.security.Principal;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import springData.domain.Shift;
import springData.domain.Timesheet;
import springData.repository.ShiftRepository;
import springData.repository.TimesheetRepository;
import springData.repository.UserRepository;

@Controller
@RequestMapping("/manager")
public class ManagerController {

   @Autowired UserRepository userRepo;
   @Autowired ShiftRepository shiftRepo;
   @Autowired TimesheetRepository timesheetRepo;

   @RequestMapping("/pending-timesheets")
   public String pendingTimesheets(Model model, Principal principal) {
      //List of Submitted Timesheets
      List<Timesheet> timesheets = (List<Timesheet>) timesheetRepo.findAllSubmitted();

      model.addAttribute("timesheets", timesheets);

      return "manager/pending-timesheets";
   }

   @RequestMapping("/approved-timesheets")
   public String approvedTimesheets(Model model, Principal principal) {
      //List of Approved Timesheets
      List<Timesheet> timesheets = (List<Timesheet>) timesheetRepo.findAllApproved();

      model.addAttribute("timesheets", timesheets);

      return "manager/approved-timesheets";
   }

   @GetMapping("/approve-timesheet/{timesheetId}")
   public String approveTimesheet(@PathVariable int timesheetId) {
      //Find Timesheet by ID
      Timesheet timesheet = timesheetRepo.findById(timesheetId);

      //Approve Timesheet
      timesheet.setApproved(true);

      //Save Timesheet
      timesheetRepo.save(timesheet);

      return "redirect:/manager/pending-timesheets";
   }

   @GetMapping("/reject-timesheet/{timesheetId}")
   public String rejectTimesheet(@PathVariable int timesheetId) {
      //Find Timesheet by ID
      Timesheet timesheet = timesheetRepo.findById(timesheetId);

      //Reject Timesheet and send back
      timesheet.setSubmitted(false);

      //Save Timesheet
      timesheetRepo.save(timesheet);

      return "redirect:/manager/pending-timesheets";
   }

   @GetMapping("/review-timesheet/{timesheetId}")
   public String reviewTimesheet(@PathVariable int timesheetId, Model model) {
      //Find Timesheet by ID
      Timesheet timesheet = timesheetRepo.findById(timesheetId);

      //Add shifts to form
      List<Shift> shifts = timesheet.getShifts();

      model.addAttribute("timesheet", timesheet);
      model.addAttribute("shifts", shifts);

      return "manager/review-timesheet";
   }

   @RequestMapping("/review-timesheet/back")
   public String reviewTimesheetBack() {
      return "redirect:/manager/approved-timesheets";
   }

}
//ManagerController
