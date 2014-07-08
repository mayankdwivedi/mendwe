package com.mendwe.controller;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import com.mendwe.DAO.MendWeDAOStd;
import com.mendwe.model.User;
import com.mendwe.utility.JedisFactory;

@Controller
public class LoginController {

	private static final Logger logger = Logger
			.getLogger(LoginController.class);
	
	@Autowired
	private MendWeDAOStd mendWeDAOStd;

	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String openourprofilePage() {

		return "login";
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String createAccount(@ModelAttribute("user") User user,
			BindingResult result, HttpServletRequest request) {
	
		mendWeDAOStd.registerFreshUser(user);
		HttpSession session = request.getSession();
		session.setAttribute("username", user.getUsername());
		

		return "redirect:home.html";
	}

	@RequestMapping(value = "/loginValidate", method = RequestMethod.POST)
	public String loginValidate(@ModelAttribute("user") User user,
			BindingResult result, HttpServletRequest request,
			final RedirectAttributes redirectAttributes) {
		Jedis jedis = JedisFactory.getInstance().getJedisPool().getResource();
        
		String username = user.getUsername();
		String password = user.getPassword();
		
			String passwordDB = jedis.get("USERNAME_PASSWORD:"+ username);
			if(passwordDB==null){
				redirectAttributes.addFlashAttribute("message",
						"User does not exist");
				return "redirect:login.html";
			}
			else{
				if (password.equals(passwordDB)) {
					logger.info("User logged in");
					HttpSession session = request.getSession();
					session.setAttribute("username", username);
					return "redirect:home.html";
				} else {
					redirectAttributes.addFlashAttribute("user", user);
					redirectAttributes.addFlashAttribute("message",
							"Incorrect username  and password");
					return "redirect:login.html";
				}
			}
		
		
	}

	@RequestMapping(value = "/logout.html", method = RequestMethod.GET)
	public String moveToLoginPage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "login";
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String printWelcome(ModelMap model, Principal principal) {

		String name = principal.getName();
		model.addAttribute("username", name);
		model.addAttribute("message", "Spring Security Custom Form example");
		return "hello";

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {

		return "login";

	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {

		model.addAttribute("error", "true");
		return "login";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {

		return "login";

	}
}
