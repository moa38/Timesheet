package springData.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springData.domain.User;
import springData.repository.PositionRepository;
import springData.repository.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	PositionRepository posRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new UserValidator(userRepo));
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Model model, String posName) {
		model.addAttribute("nUser", new User());
		return "admin/CreateUser";
	}

	@RequestMapping(value = "/create", params = "add", method = RequestMethod.POST)
	public String addUser(@RequestParam("posName") String posName, @Valid @ModelAttribute("nUser") User u, BindingResult results, Model model) {
		if (results.hasErrors()) {
			return "admin/CreateUser";
		} else {
			BCryptPasswordEncoder pe = new  BCryptPasswordEncoder();			
			u.setPassword(pe.encode(u.getPassword()));
			u.setPosition(posRepo.findByPosition(posName));
			userRepo.save(u);
			return "admin/done";
		}
	}

	@RequestMapping(value = "create", params = "cancel", method = RequestMethod.POST)
	public String cancelUser() {
		return "admin/done";
	}
}