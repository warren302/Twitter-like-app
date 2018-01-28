package pl.coderslab.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.entity.Message;
import pl.coderslab.entity.User;
import pl.coderslab.repository.MessageRepository;
import pl.coderslab.repository.UserRepository;

@Controller
@RequestMapping(path = "/message")
public class MessageController {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping(path = "/details/{id}")
	public String showMessage(@PathVariable("id") long id, HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "index";
		} else {
			Message message = messageRepository.findOne(id);
			session.setAttribute("message", message);
			model.addAttribute("message", message);
			return "message/messagedetails";
		}
	}

	@PostMapping(path = "/details/{id}")
	public String showMessage(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "index";
		} else {
			Message message = (Message) session.getAttribute("message");
			if (user.getId() == message.getAddressee().getId()) {
				message.setRead(true);
				messageRepository.save(message);
			}
			session.removeAttribute("message");
			return "redirect:/user/mailbox";
		}
	}

	@GetMapping(path = "/newmessage/{id}")
	public String newMessage(@PathVariable("id") long id, HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "index";
		} else {
			User sendTo = userRepository.findOne(id);
			model.addAttribute("sendTo", sendTo);
			session.setAttribute("sendTo", sendTo);
			model.addAttribute("message", new Message());
			return "message/newmessage";
		}
	}

	@PostMapping(path = "/newmessage/{id}")
	public String newMessage(HttpSession session, @ModelAttribute Message message) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "index";
		} else {
			User sendTo = (User) session.getAttribute("sendTo");
			if (!"".equals(message.getText())) {
				message.setSender(user);
				message.setAddressee(sendTo);
				message.setRead(false);
				message.setCreated(LocalDateTime.now());
				messageRepository.save(message);
			}
			session.removeAttribute("sendTo");
			return "redirect:/user/details/" + sendTo.getId();
		}

	}

}
