package springData.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import springData.domain.User;


public class UserValidator implements Validator {

   public boolean supports(Class<?> clazz) {
      return User.class.equals(clazz);
   }

   @Override
   public void validate(Object target, Errors errors) {
      User dto = (User) target;

      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "", "Field cannot be empty.");
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Field cannot be empty.");
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "", "Field cannot be empty.");

      if ((dto.getUserId() == 0) || (dto.getUserId() < 0)) {
         errors.rejectValue("userId", "", "Id invalid.");
      }

      if ((dto.getFirstName() != null) && (dto.getFirstName().length() < 1)) {
         errors.rejectValue("firstName", "", "The user name has less than 1 character.");
      }

   }

}
