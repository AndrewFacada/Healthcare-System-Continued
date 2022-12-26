package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil; 

public class UserDAOImpl implements UserDAO {
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");

	@Override
	public boolean createAccount(User user) {
		try (Connection connection = ConnectionUtil.getConnection()) { // first checks if email is in database
			PreparedStatement statement = connection.prepareStatement("SELECT email FROM user_info WHERE email = ?;");
			statement.setString(1, user.getEmail());
			ResultSet result = statement.executeQuery();

			if (!result.next()) { // if email is not in the database, creates new user with info provided
				PreparedStatement statementTwo = connection.prepareStatement(
						"INSERT INTO user_info(first_name, last_name, email, password, DoB, SSN, address, current_employee, current_subscriber) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
				
				LocalDate localDate = LocalDate.parse(user.getDateOfBirth(),formatter); //converts String DoB to correct format
				
				statementTwo.setString(1, user.getFirstName());
				statementTwo.setString(2, user.getLastName());
				statementTwo.setString(3, user.getEmail());
				statementTwo.setString(4, user.getPassword());
				statementTwo.setObject(5, localDate);
				statementTwo.setString(6, user.getSocialSecurityNumber());
				statementTwo.setString(7, user.getAddress());
				statementTwo.setBoolean(8, user.isCurrentEmployee());
				statementTwo.setBoolean(9, user.isCurrentSubscriber());
				int result2 = statementTwo.executeUpdate();
				return true; // returns true if user created
			}
			return false; // returns false if user not created (user already in database)

		} catch (SQLException e) {
			e.printStackTrace();
			return false; // returns false if any SQL errors
		}
	}

	@Override
	public User login(User user) {
		try (Connection connection = ConnectionUtil.getConnection()) {

			PreparedStatement statement = connection.prepareStatement(
					"Select user_id, first_name, last_name, email, password, DoB, SSN, address, current_employee, current_subscriber FROM user_info WHERE email = ? AND password = ?;");
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			ResultSet result = statement.executeQuery();

			User a = new User(); // creates new object to return logged in user if credentials are correct
			
			if (result.next()) { // confirms that user exists and has been provided correct credentials if next()
				a.setUser_id(result.getString("user_id")); 
				a.setFirstName(result.getString("first_name"));
				a.setLastName(result.getString("last_name"));
				a.setEmail(result.getString("email"));
				a.setPassword(result.getString("password"));
				a.setDateOfBirth(result.getString("DoB"));
				a.setSocialSecurityNumber(result.getString("SSN"));
				a.setAddress(result.getString("address"));
				a.setCurrentEmployee(result.getBoolean("current_employee"));
				a.setCurrentSubscriber(result.getBoolean("current_subscriber"));
				
				return a; // returns user object if credentials matched
			} else {
				return null; // returns null if credentials did not match
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	@Override
	public List<User> viewAllUsers() {
		try(Connection connection = ConnectionUtil.getConnection()){
			String sql = "Select * FROM user_info;";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			List<User> list = new ArrayList<>(); //creates new list for user objects
			
			
			
			while(result.next()) {
				User user = new User();
				
				user.setUser_id(result.getString("user_id")); 
				user.setFirstName(result.getString("first_name"));
				user.setLastName(result.getString("last_name"));
				user.setEmail(result.getString("email"));
				user.setPassword(result.getString("password"));
				user.setDateOfBirth(result.getString("DoB"));
				user.setSocialSecurityNumber(result.getString("SSN"));
				user.setAddress(result.getString("address"));
				user.setCurrentEmployee(result.getBoolean("current_employee"));
				user.setCurrentSubscriber(result.getBoolean("current_subscriber"));
				
				list.add(user); //adds user object to list
			}
			return list; //returns list of user object
			
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean resetPassword(User user) {
		try (Connection connection = ConnectionUtil.getConnection()) { //checks to be sure email and ssn match
			PreparedStatement checker = connection.prepareStatement("SELECT * FROM user_info WHERE email = ? and ssn = ?;");
			checker.setString(1, user.getEmail());
			checker.setString(2, user.getSocialSecurityNumber());
			ResultSet result = checker.executeQuery();
			
			if(result.next()) { //if account info with email and ssn match, password updates
				PreparedStatement statement = connection.prepareStatement(
						"UPDATE user_info SET password = ? WHERE email = ?;");
				statement.setString(1, user.getPassword());
				statement.setString(2, user.getEmail());
				int resultTwo = statement.executeUpdate();
				
				return true; //password was updated
				
			}else {
				return false; //password was not updated
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
