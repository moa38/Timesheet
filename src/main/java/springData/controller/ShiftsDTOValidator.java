package springData.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import springData.DTO.ShiftsDTO;
import springData.domain.Shift;

public class ShiftsDTOValidator implements Validator {

   public boolean supports(Class<?> clazz) {
      return ShiftsDTO.class.equals(clazz);
   }

   @Override
   public void validate(Object target, Errors errors) {
      ShiftsDTO dto = (ShiftsDTO) target;

      //Validate each Shift
      for(int i = 0; i < dto.getShifts().size(); i++) {
         //Get Shift from DTO
         Shift shift = dto.getShifts().get(i);

         //Ensure Shift has valid ID
         if (shift.getShiftId() <= -1) {
            errors.rejectValue("shifts[" + i + "].shiftId", "", "ShiftId invalid");
         }
         //Check ShiftDate field !=null
         if (shift.getShiftDate() == null) {
            errors.rejectValue("shifts[" + i + "].shiftDate", "", "Please choose a date");
         }
         //Ensure StartTime field !=null
         if (shift.getStartTime() == null) {
            errors.rejectValue("shifts[" + i + "].startTime", "", "Start time must be entered");
         } 
         //Ensure EndTime field !=null
         if (shift.getEndTime() == null) {
            errors.rejectValue("shifts[" + i + "].endTime", "", "End time must be entered");
         }
         //Ensure EndTime != StartTime
         if (shift.getEndTime() == shift.getStartTime())  {
            errors.rejectValue("shifts[" + i + "].endTime", "", "End & start time can not be the same");
         }
      }
   }

}
//ShiftsDTOValidator