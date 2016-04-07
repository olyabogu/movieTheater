package com.epam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.domain.User;
import com.epam.services.UserService;
import com.epam.util.SecurityUtils;

/**
 * @author Olga_Bogutska.
 */
@Controller
public class ViewBalanceController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = Mappings.USER_BALANCE, method = RequestMethod.GET)
	public ModelAndView viewBalance(ModelAndView mv) {
		User user = SecurityUtils.getLoggedUser();
		mv.addObject("user", user);
		return mv;
	}

	@RequestMapping(value = Mappings.USER_BALANCE, method = RequestMethod.POST)
	public String refillBalance(@RequestParam("amount") String amount) {
		if(!StringUtils.isEmpty(amount)) {

		}
		return "redirect:balance";
	}

	@RequestMapping(value = Mappings.USER_BALANCE, params = "cancel", method = RequestMethod.POST)
	public String cancelRegisterUser() {
		return "redirect:index";
	}
}
