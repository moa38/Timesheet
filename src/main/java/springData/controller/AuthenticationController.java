package springData.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController {

	// @RequestMapping(value = "/")
	// public String landing() {
	// return "security/login";
	// }

	@RequestMapping(value = "/sec-login", method = RequestMethod.GET)
	public String loginForm() {
		return "/security/login";
	}
}