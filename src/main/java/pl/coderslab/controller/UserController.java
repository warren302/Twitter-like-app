package pl.coderslab.controller;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import pl.coderslab.entity.Tweet;
import pl.coderslab.entity.User;
import pl.coderslab.repository.TweetRepository;
import pl.coderslab.repository.UserRepository;

@Controller
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TweetRepository tweetRepository;

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
			return "redirect:/tweet/list";
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
			user.setEnabled(true);
			userRepository.save(user);
			return "redirect:login";

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

	@GetMapping(path = "/logout")
	public String processLogOutRequest(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user != null) {

			return "user/logout";
		} else {
			return "index";
		}
	}

	@PostMapping(path = "/logout")
	public String processLogOutRequest(HttpServletRequest request, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			String button = request.getParameter("button");
			if ("Confirm".equals(button)) {
				session.removeAttribute("user");
				return "index";
			} else
				return "redirect:/tweet/list";
		} else {
			return "index";
		}
	}

	@GetMapping(path = "/details/{id}")
	public String showUser(@PathVariable("id") long id, HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			User showUser = userRepository.findOne(id);
			if (showUser != null) {
				if (showUser.getTweets() != null) {
					Collections.reverse(showUser.getTweets());
				}
				model.addAttribute("showUser", showUser);
				long twCounter = tweetRepository.countByUser(showUser);
				model.addAttribute("twCounter", twCounter);
				return "user/userdetails";
			} else {
				return "user/usernotfound";
			}
		} else {
			return "index";
		}
	}

}
