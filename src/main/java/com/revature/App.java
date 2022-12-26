package com.revature;


import com.revature.controllers.ClaimController;
import com.revature.controllers.Controller;
import com.revature.controllers.CovidInfoController;
import com.revature.controllers.UserController;

import io.javalin.Javalin;

public class App {
private static Javalin app;
	public static void main(String[] args) {
		app= Javalin.create();
		app.get("/hello",(ctx)->{
			String url = ctx.url();
			System.out.println(url);
			ctx.html("<h1> Hello Javalin</h1>");
			ctx.status(200);
		} );
		
		configure(new UserController(), new ClaimController(), new CovidInfoController());

		app.start();
	}

	public static void configure(Controller... controllers) {
		for(Controller c:controllers) {
			c.addRoutes(app);
		}
	}
}
