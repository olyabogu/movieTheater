package com.epam.domain;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.epam.controller.Mappings;

/**
 * @author Olga_Bogutska.
 */
@Component
public class Command {
	private String name;
	private String description;

	public Command() {
	}

	public Command(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Command> getCommands() {
		List<Command> commands = new LinkedList<>();
		commands.add(new Command(Mappings.ADD_EVENT, "Add new event"));
		commands.add(new Command(Mappings.REGISTER_USER, "Register new user"));
		commands.add(new Command(Mappings.USER_BALANCE, "View user balance"));
		commands.add(new Command(Mappings.VIEW_EVENTS, "View events with air dates and time"));
		commands.add(new Command(Mappings.VIEW_TICKETS, "Shows tickets"));
		return commands;
	}
}
