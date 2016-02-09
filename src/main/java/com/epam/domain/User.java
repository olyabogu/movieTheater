package com.epam.domain;

import java.util.List;

/**
 * Created by Olga Bogutska on 08.02.2016.
 */

public class User {
    private int id;
    private String name;
    private UserRole role;
    private String email;
    private List<Ticket> bookedTickets;

    public enum UserRole{
        ADMIN, CLIENT, ANONYM
    }

    public User(String name, UserRole role, String email) {
        this.id = id++;
        this.name = name;
        this.role = role;
        this.email = email;
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
