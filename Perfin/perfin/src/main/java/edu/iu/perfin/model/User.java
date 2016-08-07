package edu.iu.perfin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_PERFIN_USER")
public class User {

	@Id
	@Column(name = "USERID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PERFIN_USER")
	@SequenceGenerator(name = "S_PERFIN_USER", sequenceName = "S_PERFIN_USER", allocationSize = 1, initialValue = 1)
	private Long userId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "SURNAME")
	private String surname;
	
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PASSWORD")
	private String password;

	@ManyToOne
	@JoinColumn(name = "ACCOUNT")
	private Account account;

	public User() {
		super();
	}

	public User(String name, Long userId, String surname, String username, String email, String password,
			Account account) {
		super();
		this.userId = userId;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.account = account;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}