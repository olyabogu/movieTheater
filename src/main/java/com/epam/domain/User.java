package com.epam.domain;

import com.epam.domain.adapter.DateAdapter;
import com.epam.util.SecurityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.util.*;

/**
 * Created by Olga Bogutska on 08.02.2016.
 */
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements UserDetails {
    @XmlAttribute(required=true)
    private int id;
    @XmlAttribute(required=true)
    private String username;
	@XmlAttribute(required = true)
	private String password;
    @XmlAttribute(required=true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date birthDate;
	@XmlElement(name = "roles")
	private Set<String> roles = new HashSet<>();
    @XmlAttribute(required=true)
    private String email;
	@XmlElementWrapper(name = "bookedTickets")
	@XmlElement(name = "bookedTicket")
	private List<Ticket> bookedTickets;
	@XmlTransient
	private UserAccount account;

	@XmlTransient
	private boolean accountNonExpired = true;
	@XmlTransient
    private boolean accountNonLocked = true;
	@XmlTransient
    private boolean credentialsNonExpired = true;
	@XmlTransient
    private boolean enabled = true;

	@XmlTransient
	private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities == null) {
            authorities = SecurityUtils.toAuthorities(roles);
        }
        return authorities;
    }

    public User() {
    }

    public User(String name, Date birthDate, Set<String> roles, String email) {
        this.birthDate = birthDate;
        this.username = name;
        this.roles = roles;
        this.email = email;
	    this.bookedTickets = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
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
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

	public UserAccount getAccount() {
		return account;
	}

	public void setAccount(UserAccount account) {
		this.account = account;
	}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", role=" + roles.toString() +
                ", email='" + email + '\'' +
                ", bookedTickets=" + bookedTickets +
                '}';
    }
}
