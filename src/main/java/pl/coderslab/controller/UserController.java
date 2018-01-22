package pl.coderslab.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;

@Controller()
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping(path = "/login")
	public String showLoginForm() {
		return "user/login";
	}

	@PostMapping(path = "/login")
	public String processLoginRequest(@RequestParam("username") String username,
			@RequestParam("password") String password, Model model, HttpSession session) {

		User user = userRepository.findOneByUsernameAndPassword(username, password);

		if (user != null) {

			session.setAttribute("user", user);
			model.addAttribute("username", username);
			return "user/success";

		} else {

			return "user/login";
		}
	}

	@GetMapping(path = "/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("user", new User());
		return "user/register";
	}

	@PostMapping(path = "/register")
	public String processRegistartionRequest(@Valid User user, BindingResult bresult) {

		if (bresult.hasErrors()) {

			return "user/register";

		} else {

			userRepository.save(user);
			return "user/success";

		}
	}

	@GetMapping(path = "/settings")
	public String showUserSettingsPage(@RequestParam("id") long id, Model model,
			@SessionAttribute(name = "user", required = false) User user) {
		if (user != null) {
			model.addAttribute("user", user);
			return "user/settings";
		} else {
			return "user/login";
		}
	}

}
