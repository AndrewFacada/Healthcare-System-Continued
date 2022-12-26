package com.revature.models;

public class User {
	private String user_id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String dateOfBirth;
	private String socialSecurityNumber;
	private String address;
	private boolean currentEmployee;
	private boolean currentSubscriber;
	


	//constructor for creating a new user
	public User(String firstName, String lastName, String email, String password, String dateOfBirth,
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
	public User(String user_id, String firstName, String lastName, String email, String password, String dateOfBirth,
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
	
	
	
	

	public String getUser_id() {
		return user_id;
	}



	public void setUser_id(String user_id) {
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

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
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
