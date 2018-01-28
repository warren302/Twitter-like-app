package pl.coderslab.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import pl.coderslab.entity.Message;
import pl.coderslab.entity.Tweet;
import pl.coderslab.entity.User;
import pl.coderslab.form.LoginForm;
import pl.coderslab.repository.CommentRepository;
import pl.coderslab.repository.MessageRepository;
import pl.coderslab.repository.TweetRepository;
import pl.coderslab.repository.UserRepository;

@Controller
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TweetRepository tweetRepository;
	
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private MessageRepository messageRepository;

	
	@GetMapping(path = "/login")
	public String showLoginForm(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "user/login";
	}

	@PostMapping(path = "/login")
	public String processLoginRequest(@Valid LoginForm loginForm, BindingResult result, HttpSession session) {
		
		User user = userRepository.findOneByUsername(loginForm.getUsername());
		if (user != null && BCrypt.checkpw(loginForm.getPassword(), user.getPassword())) {
			session.setAttribute("user", user);
			return "redirect:/tweet/list";
		} else {
			result.reject("login.error", "invalid credentials");
			result.getModel().put("password", "");
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
	public String processLogOutRequest(HttpSession session) {
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
				showUser.setTweets((List<Tweet>) tweetRepository.findByUserIdDesc(showUser.getId()));
				model.addAttribute("showUser", showUser);
				long twCounter = tweetRepository.countByUser(showUser);
				model.addAttribute("twCounter", twCounter);
				long comCounter = commentRepository.countByUserId(showUser.getId());
				model.addAttribute("comCounter",comCounter);
				return "user/userdetails";
			} else {
				return "user/usernotfound";
			}
		} else {
			return "index";
		}
	}

	@GetMapping(path = "/mailbox")
	public String showMailbox(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			List<User> receivers = userRepository.findAll();
			receivers.removeIf(o -> o.getId() == user.getId());
			model.addAttribute("receivers", receivers);
			List<Message> messages = (List<Message>) messageRepository.findAllBySenderOrAddressee(user, user);
			model.addAttribute("messages", messages);
			model.addAttribute("message", new Message());
			return "user/mailbox";
		} else {
			return "index";
		}
	}
	
	
	@PostMapping(path = "/mailbox")
	public String showMailbox(HttpSession session, @ModelAttribute Message message) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "index";
		} else {
			if (message != null && !"".equals(message.getText())) {
				message.setCreated(LocalDateTime.now());
				message.setSender(user);
				message.setRead(false);
				messageRepository.save(message);
			}
			return "redirect:mailbox";
		}
	}
	
}
