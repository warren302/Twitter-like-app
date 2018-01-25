package pl.coderslab.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.entity.Comment;
import pl.coderslab.entity.Tweet;
import pl.coderslab.entity.User;
import pl.coderslab.repository.CommentRepository;
import pl.coderslab.repository.TweetRepository;

@Controller
@RequestMapping(path = "/tweet")
public class TweetController {

	@Autowired
	private TweetRepository tweetRepository;

	@Autowired
	private CommentRepository commentRepository;

	@GetMapping(path = "/list")
	public String listAllTweets(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			List<Tweet> tweets = (ArrayList<Tweet>) tweetRepository.findAllByOrderByCreatedDesc();
			model.addAttribute("tweets", tweets);
			model.addAttribute("tweet", new Tweet());
			return "/tweet/listoftweets";
		} else {
			return "/index";
		}

	}

	@PostMapping(path = "/list")
	public String listAllTweets(HttpSession session, @ModelAttribute Tweet tweet) {
		if (tweet != null && !"".equals(tweet.getText())) {
			User user = (User) session.getAttribute("user");
			if (user != null) {
				tweet.setUser(user);
				tweet.setCreated(LocalDateTime.now());
				tweetRepository.save(tweet);
				return "redirect:list";
			} else {
				return "index";
			}
		} else {
			return "redirect:list";
		}
	}

	@GetMapping(path = "/details/{id}")
	public String tweetDetails(@PathVariable("id") long id, HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			Tweet tweet = tweetRepository.findOne(id);
			if (tweet != null) {
				Collections.reverse(tweet.getComments());
				model.addAttribute("tweet", tweet);
				model.addAttribute("comment", new Comment());
				session.setAttribute("tweet", tweet);
				return "/tweet/tweetdetails";

			} else {
				return "tweet/tweetnotfound";
			}
		} else {
			return "index";
		}
	}


	@PostMapping(path = "/details/{id}")
	public String tweetDetails(HttpSession session, @ModelAttribute Comment comment) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "index";
		} else {
			Tweet tweet = (Tweet) session.getAttribute("tweet");
			session.removeAttribute("tweet");
			if (comment != null && !"".equals(comment.getText())) {
				comment.setUser(user);
				comment.setTweet(tweet);
				comment.setCreated(LocalDateTime.now());
				commentRepository.save(comment);
			}
			return "redirect:/tweet/details/" + tweet.getId();
		}
	}

}
