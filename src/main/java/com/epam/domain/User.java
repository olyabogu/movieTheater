package com.epam.domain;

import com.epam.domain.adapter.DateAdapter;
import com.epam.util.SecurityUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Size;
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
    @Size(min=3, max=30)
    private String username;

	@NotEmpty
	@XmlAttribute(required = true)
	private String password;

    @XmlAttribute(required=true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date birthDate;

	@XmlElement(name = "roles")
	private Set<String> roles = new HashSet<>();

	@Email
	@NotEmpty
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
	@JsonIgnore
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;

		User user = (User) o;

		if (id != user.id) return false;
		if (!username.equals(user.username)) return false;
		if (!password.equals(user.password)) return false;
		if (!birthDate.equals(user.birthDate)) return false;
		if (!roles.equals(user.roles)) return false;
		if (!email.equals(user.email)) return false;
		if (bookedTickets != null ? !bookedTickets.equals(user.bookedTickets) : user.bookedTickets != null) return false;
		return account != null ? account.equals(user.account) : user.account == null;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + username.hashCode();
		result = 31 * result + password.hashCode();
		result = 31 * result + birthDate.hashCode();
		result = 31 * result + roles.hashCode();
		result = 31 * result + email.hashCode();
		result = 31 * result + (bookedTickets != null ? bookedTickets.hashCode() : 0);
		result = 31 * result + (account != null ? account.hashCode() : 0);
		return result;
	}
}
