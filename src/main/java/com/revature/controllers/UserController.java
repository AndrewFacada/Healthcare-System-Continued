package com.revature.controllers;

import com.revature.models.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.revature.services.UserService;

import java.time.format.DateTimeFormatter;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import jakarta.servlet.http.HttpSession;

public class UserController implements Controller{
	static HttpSession sess;
	private String tempEmail;
	
	
	private UserService userService = new UserService();
	
	
	Handler login = (ctx) -> {
		User user = ctx.bodyAsClass(User.class); //creates new user from JSON body
		
		if(user.getEmail().isEmpty() || user.getPassword().isEmpty()){ //Checks to be sure fields are not empty
			ctx.json("You must fill in all fields!");
			ctx.status(400);
		}else {
			if(userService.logIn(user) != null) { //returns null only if credentials do not match
				sess = ctx.req().getSession(); //creates new HttpSession session
				tempEmail = "not null";
				sess.setAttribute("firstName", userService.logIn(user).getFirstName()); //Sets all attributes for HTTPSession per logging in user
				sess.setAttribute("lastName", userService.logIn(user).getLastName());
				sess.setAttribute("email", userService.logIn(user).getEmail());
				sess.setAttribute("password", userService.logIn(user).getPassword());
				sess.setAttribute("DoB", userService.logIn(user).getDateOfBirth());
				sess.setAttribute("SSN", userService.logIn(user).getSocialSecurityNumber());
				sess.setAttribute("address", userService.logIn(user).getAddress());
				sess.setAttribute("currentEmployee", userService.logIn(user).isCurrentEmployee());
				sess.setAttribute("currentSubscriber", userService.logIn(user).isCurrentSubscriber());
				
				String info = "Logged in with the  email: " + sess.getAttribute("email");
				ctx.json(info);
				ctx.status(201);
			}else {
				String info = "Email or password are incorrect!";
				ctx.json(info);
				ctx.status(401);
			}
		}
		
	};
	
	
	Handler createAccount = (ctx) ->{
		User user = ctx.bodyAsClass(User.class); //creates new user from JSON body
		
		if(user.getFirstName().isEmpty() || user.getLastName().isEmpty() ||     //Checks to be sure fields are not empty
				user.getEmail().isEmpty() || !user.getEmail().contains("@") ||user.getPassword().isEmpty() || 
				user.getSocialSecurityNumber().isEmpty() || user.getAddress().isEmpty()) {
			
			ctx.json("You must fill in all fields!");
			ctx.status(400);
		}else {
			//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
			//LocalDate localDate = LocalDate.parse(user.getDateOfBirth(),formatter);
			//user.setDateOfBirth(localDate);
			if(userService.newAccount(user) == false) { //if false means account already exists under that email
				String temp = "An account already exists with that email!";
				ctx.json(temp);
				ctx.status(409);
			}else {  //means account successfully created with that email
				String temp = "Account created for email: " + user.getEmail();
				ctx.json(temp);
				ctx.status(201);
			}
		}
		
	};
	
	
	
	Handler viewAllAccounts = (ctx) ->{
		if(userService.viewAllAccounts() == null) { //returns null if no accounts in the database
			ctx.json("No Accounts created!");
			ctx.status(404);
		}else {  //else there are accounts in the database and it will show them
			ctx.json(userService.viewAllAccounts());
			ctx.status(200);
		}
		
	};
	
	
	Handler updatePassword = (ctx) ->{
		User user = ctx.bodyAsClass(User.class);
		if(user.getEmail().isEmpty() || user.getPassword().isEmpty()||
				user.getSocialSecurityNumber().isEmpty()){
			
			ctx.json("You must fill in all fields!");
			ctx.status(400);
		}else {
			if(userService.updatePassword(user)) {
				ctx.json("Password has been reset!");
				ctx.status(200);
			}else {
				ctx.json("Password has not been reset!");
			}
		}
	};
	
	
	@Override
	public void addRoutes(Javalin app) {
		app.get("/login", login);
		app.post("/newuser", createAccount);
		app.get("/allaccounts", viewAllAccounts);
		app.patch("/updatepassword",updatePassword);
		
	}


}
