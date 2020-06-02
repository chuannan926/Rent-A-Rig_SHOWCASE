package cova.rar.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cova.rar.entities.User;
import cova.rar.service.CookieMonster;
import cova.rar.service.UserService;

@Controller
public class MyAccountController {
	
	@Autowired
	CookieMonster cookieMonster;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = {"/myAccount"})
	public ModelAndView myAccount(@CookieValue("username") String username, HttpServletRequest request, HttpServletResponse response) {

		// assume user is logged in since link to myaccount is hidden
		
		User user = userService.getUser(username);
		
		if (null == cookieMonster.getCookie("firstname", request)) {
			userService.setUserCookies(request, response);
			return new ModelAndView("myAccount", "user", user);
		}
		else {
			return new ModelAndView("myAccount", "user", user);
		}
	}
	
}
