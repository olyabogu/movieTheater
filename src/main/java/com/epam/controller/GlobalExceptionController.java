package com.epam.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.epam.exception.MovieException;

/**
 * @author Olga_Bogutska.
 */
@ControllerAdvice
public class GlobalExceptionController {

	@ExceptionHandler(MovieException.class)
	public ModelAndView handleCustomException(MovieException e) {
		ModelAndView model = new ModelAndView(Mappings.ERROR_PAGE);
		model.addObject("errorMsg", e.getErrorMsg());
		return model;
	}


	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception e) {
		ModelAndView model = new ModelAndView(Mappings.ERROR_PAGE);
		model.addObject("errorMsg", e.getMessage());
		return model;
	}
}
