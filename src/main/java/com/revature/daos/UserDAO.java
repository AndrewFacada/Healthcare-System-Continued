package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {

	public abstract boolean createAccount(User user);
	
	public abstract User login(User user);
	
	public abstract List<User> viewAllUsers();
	
	public abstract boolean resetPassword(User user);

}
