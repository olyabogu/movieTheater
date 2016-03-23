package com.epam.controller;

import com.epam.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private List<Command> commands;

    @RequestMapping(Mappings.INDEX_PAGE)
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("commands", commands);
        return modelAndView;
    }

    @RequestMapping("/error.page")
    public String error() {
        return "error";
    }
}
