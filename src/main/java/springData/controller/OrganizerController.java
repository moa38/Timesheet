package springData.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springData.domain.Organizer;
import springData.domain.OrganizerUser;
import springData.domain.Todo;
import springData.repository.TodoRepository;
import springData.repository.UserRepository;

@Controller
@RequestMapping("/")
public class OrganizerController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private TodoRepository todoRepo;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new TodoValidator());
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("todo", new Todo());
		return "CreateTodo";
	}

	@RequestMapping(value = "create", params = "add", method = RequestMethod.POST)
	public String addNewTodo(@Valid @ModelAttribute("todo") Todo t, BindingResult result, Model model, Principal principal) {
		
		if (result.hasErrors()) {
			return "CreateTodo";
		} else {
			OrganizerUser user = userRepo.findByLogin(principal.getName());
			// add organizer if necessary
			if (user.getOrganizers().isEmpty()) {
				Organizer o = new Organizer();
				user.getOrganizers().add(o);
				o.setOwner(user);
			}
			user.getOrganizers().get(0).addTodo(t);
			userRepo.save(user);
			return "redirect:/list";
		}
	}

	@RequestMapping(value = "create", params = "cancel", method = RequestMethod.POST)
	public String cancelNewTodo() {
		return "redirect:/list";
	}

	@RequestMapping(value = "delete", params = "id", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam("id") int id, Principal principal) {
		Todo t = todoRepo.findById(id);		
		if (t != null) {
			// deleting the todo will fail a foreign key constraint
			OrganizerUser user = userRepo.findByLogin(principal.getName());
			for (Organizer o : user.getOrganizers()) {
				if (o.getTodos().contains(t)) {
					o.deleteTodo(t.getId());					
				}
			}
			// delete todo as orphan
			userRepo.save(user);
		}
		return "redirect:/list";
	}
}
