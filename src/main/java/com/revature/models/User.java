package com.revature.models;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "user_info")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	
	private String email;
	
	
	private String password;
	
	@Column(name = "dob")
	private LocalDate dateOfBirth;
	
	@Column(name = "ssn")
	private String socialSecurityNumber;
	
	
	private String address;
	
	@Column(name = "current_employee")
	private boolean currentEmployee;
	
	@Column(name = "current_subscriber")
	private boolean currentSubscriber;
	


	//constructor for creating a new user
	public User(String firstName, String lastName, String email, String password, LocalDate dateOfBirth,
			String socialSecurityNumber, String address, boolean currentEmployee, boolean currentSubscriber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.socialSecurityNumber = socialSecurityNumber;
		this.address = address;
		this.currentEmployee = currentEmployee;
		this.currentSubscriber = currentSubscriber;
	}
	
	

	//constructor for logging in part 1
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	
	//constructor for logging in part 2
	public User(int user_id, String firstName, String lastName, String email, String password, LocalDate dateOfBirth,
			String socialSecurityNumber, String address, boolean currentEmployee, boolean currentSubscriber) {
		super();
		this.user_id = user_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.socialSecurityNumber = socialSecurityNumber;
		this.address = address;
		this.currentEmployee = currentEmployee;
		this.currentSubscriber = currentSubscriber;
	}


	
	
	
	//Constructor for resetting password
	public User(String email, String socialSecurityNumber, String password) {
		super();
		this.email = email;
		this.password = password;
		this.socialSecurityNumber = socialSecurityNumber;
	}



	public User() {
		super();
	}
	
	
	
	

	public int getUser_id() {
		return user_id;
	}



	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}



	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isCurrentEmployee() {
		return currentEmployee;
	}

	public void setCurrentEmployee(boolean currentEmployee) {
		this.currentEmployee = currentEmployee;
	}

	public boolean isCurrentSubscriber() {
		return currentSubscriber;
	}

	public void setCurrentSubscriber(boolean currentSubscriber) {
		this.currentSubscriber = currentSubscriber;
	}



	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", dateOfBirth=" + dateOfBirth + ", socialSecurityNumber="
				+ socialSecurityNumber + ", address=" + address + ", currentEmployee=" + currentEmployee
				+ ", currentSubscriber=" + currentSubscriber + "]";
	}
}
