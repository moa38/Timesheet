package springData.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springData.domain.Organizer;
import springData.domain.OrganizerUser;
import springData.domain.Todo;
import springData.repository.TodoRepository;
import springData.repository.UserRepository;

@Controller
@RequestMapping("/")
public class DisplayTodoController {

	@Autowired
	UserRepository userRepo;

	@Autowired
	TodoRepository todoRepo;

	@RequestMapping("/")
	public String start() {
		return "redirect:/list";
	}

	@RequestMapping("/list")
	public String listTodos(Model model, Principal principal) {


		OrganizerUser user = userRepo.findByLogin(principal.getName());
		
		List<Todo> todos = new ArrayList<>();
		addTodosForUser(todos, user);

		if (todos.isEmpty()) {
			return "NoTodo";
		} else {
			model.addAttribute("todos", todos);
		}
		return "ListTodos";
	}

	private void addTodosForUser(List<Todo> todos, OrganizerUser user) {
		if ("MANAGER".equals(user.getRole().getRole())) {
			// add todos of the manager from all organizers
			for (Organizer o : user.getOrganizers()) {
				todos.addAll(o.getTodos());
			}
		} else if ("ASSISTANT".equals(user.getRole().getRole())) {
			// add all todos in the system
			for (Todo t : todoRepo.findAll()) {
				todos.add(t);
			}
		}
	}

	@RequestMapping(value = "/next", method = RequestMethod.GET)
	public String next(Model model, Principal principal) {
		OrganizerUser user = userRepo.findByLogin(principal.getName());
		
		List<Todo> todos = new ArrayList<>();
		addTodosForUser(todos, user);

		if (todos.isEmpty()) {
			return "NoTodo";
		}
		
		Todo highest = todos.get(0);
		for (Todo t : todos) {
			if (t.getPriority() > highest.getPriority()) {
				highest = t;
			}
		}
		model.addAttribute("todo", highest);
		return "NextTodo";
	}

}
