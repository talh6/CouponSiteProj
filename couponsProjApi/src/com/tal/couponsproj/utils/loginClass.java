package com.tal.couponsproj.utils;

import com.tal.couponsproj.enums.errorType;
import com.tal.couponsproj.enums.loginType;
import com.tal.couponsproj.exeptions.BuisnesException;
import com.tal.couponsproj.exeptions.CouponException;
import com.tal.couponsproj.logic.CompanyController;
import com.tal.couponsproj.logic.CustomerController;

/**
 * Class of login to the coupon system
 * 
 * @author Tal Hakmon
 *
 */
public final class loginClass {
	//The class and login functions is final so no one can overide the login and login
	
	/**
	 * login function for tthe coupon site , the function is final so it cant be overite
	 * 
	 * @param username
	 * @param password
	 * @param type
	 * @throws CouponException
	 */
	public static final void login(String username,String password,loginType type) throws CouponException
	{
		if(type == loginType.ADMIN)
		{
			if(!username.equals("admin") || !password.equals("1234"))
				throw new BuisnesException(FinalsClass.WRONG_LOGIN_INFO ,errorType.BUISNESS_ERROR);
				
		}
		else if(type == loginType.COMPANY)
		{
			CompanyController companyController = new CompanyController();
			if(!companyController.login(username, password))
				throw new BuisnesException(FinalsClass.WRONG_LOGIN_INFO ,errorType.BUISNESS_ERROR);
		}
		else if(type == loginType.CUSTOMER)
		{
			CustomerController customerController = new CustomerController();
			if(!customerController.login(username, password))
				throw new BuisnesException(FinalsClass.WRONG_LOGIN_INFO ,errorType.BUISNESS_ERROR);	
		}
		
		else
		{
			throw new BuisnesException(FinalsClass.UNAVAILABLE_SERVICE,errorType.INPUT_ERROR);
		}
	}
}
