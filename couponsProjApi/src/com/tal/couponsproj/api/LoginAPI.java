package com.tal.couponsproj.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.tal.couponsproj.beans.Company;
import com.tal.couponsproj.beans.Customer;
import com.tal.couponsproj.beans.LoginInput;
import com.tal.couponsproj.enums.errorType;
import com.tal.couponsproj.enums.loginType;
import com.tal.couponsproj.exeptions.BuisnesException;
import com.tal.couponsproj.exeptions.CouponException;
import com.tal.couponsproj.logic.CompanyController;
import com.tal.couponsproj.logic.CustomerController;
import com.tal.couponsproj.utils.FinalsClass;
import com.tal.couponsproj.utils.loginClass;

@Path("/login")
public class LoginAPI {
	@Context private HttpServletRequest request;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void login(LoginInput loginInput) throws CouponException{
		//call to login utill
		loginClass.login(loginInput.getUsername(), loginInput.getPassword(), loginInput.getType());
		//if the login fail it will throw exception and wont get to the session
		
		request.getSession(true);
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public long getIdFromSession() {
		long id = (long) request.getAttribute("id");
		return id;
	}
	
	
}
