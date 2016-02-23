package com.epam.domain;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Olga Bogutska on 08.02.2016.
 */

public class User {
    private int id;
    private String name;
	private Date birthDate;
    private UserRole role;
    private String email;
    private List<Ticket> bookedTickets;

	public enum UserRole {
		ADMIN, CLIENT, ANONYM
	}

    public User() {
    }

    public User(String name, Date birthDate, UserRole role, String email) {
        this.birthDate = birthDate;
        this.name = name;
        this.role = role;
        this.email = email;
	    bookedTickets = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Ticket> getBookedTickets() {
        return bookedTickets;
    }

    public void setBookedTickets(List<Ticket> bookedTickets) {
        this.bookedTickets = bookedTickets;
    }

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", bookedTickets=" + bookedTickets +
                '}';
    }
}
