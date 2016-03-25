package com.epam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.domain.Command;

@Controller
public class HomeController {

    @Autowired
    private Command command;

    @RequestMapping(Mappings.INDEX_PAGE)
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("commands", command.getCommands());
        return modelAndView;
    }
}
