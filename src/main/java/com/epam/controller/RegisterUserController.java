package com.epam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.controller.model.UserModel;
import com.epam.converter.UserConverter;
import com.epam.domain.User;
import com.epam.domain.UserRole;
import com.epam.exception.MovieException;
import com.epam.services.UserService;

/**
 * @author Olga_Bogutska.
 */
@Controller
public class RegisterUserController {

	@Autowired
	private UserConverter userConverter;
	@Autowired
	private UserService userService;

	@RequestMapping(value = Mappings.REGISTER_USER, method = RequestMethod.GET)
	public ModelAndView addEvent(ModelAndView mv) {
		mv.addObject("roles", UserRole.values());
		mv.setViewName("registerUser");
		return mv;
	}

	@RequestMapping(value = Mappings.REGISTER_USER, method = RequestMethod.POST)
	public String add(@ModelAttribute("user") UserModel userModel) throws MovieException {
		User user = userConverter.toUser(userModel);
		userService.register(user);
		return "redirect:index";
	}

	@RequestMapping(value = Mappings.REGISTER_USER, params = "cancel", method = RequestMethod.POST)
	public String cancelRegisterUser() {
		return "redirect:login";
	}
}
