package springData.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import springData.domain.User;
import springData.repository.UserRepository;

public class UserValidator implements Validator {

    public boolean supports(Class<?> c) {
		return User.class.equals(c);
	}
	
	private UserRepository userRepo;
	
	public UserValidator(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
    @Override
    public void validate(Object target, Errors errors) {
		    User u = (User) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "", "Field cannot be empty.");
		
		if (userRepo.findByUserId(u.getUserId()) != null) {
			errors.rejectValue("login", "", "User with that username already exists.");
		}

    }


}